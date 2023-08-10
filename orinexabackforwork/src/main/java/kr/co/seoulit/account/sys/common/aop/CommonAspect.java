package kr.co.seoulit.account.sys.common.aop;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import kr.co.seoulit.account.sys.base.exception.IdNotFoundException;
import kr.co.seoulit.account.sys.base.exception.PwMissmatchException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;


import lombok.extern.slf4j.Slf4j;

@ControllerAdvice 
@Slf4j
@Component
@Aspect
@Configuration
public class CommonAspect {
	
    @org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)//익셉션 어노테이션. UserNotFoundException.class(우리가 새로 만든 익셉션) 도 선언할 수 있다.
     public ModelAndView idNotFoundExceptionHandler(HttpServletRequest request, IdNotFoundException e) {
        ModelAndView mv = new ModelAndView("/errorPage"); 
        mv.addObject("errorCode", -1);
        mv.addObject("errorMsg", e.getMessage());
         System.out.println("#######################IdNotFoundException#################1");
                 
         log.error("Request: " + request.getRequestURL() +"\n"+ " raised " + e); 
         
         return mv;
     }
    
     @org.springframework.web.bind.annotation.ExceptionHandler(PwMissmatchException.class)
     public ModelAndView pwMissMatchException(HttpServletRequest request, PwMissmatchException e) { //패스워드가일치하지않을때나는오류
        ModelAndView mv = new ModelAndView("/errorPage");//예외가 발생하면 여기 경로로 가라.
        mv.addObject("errorCode", -4);
        mv.addObject("errorMsg", e.getMessage());
         System.out.println("#######################PwMissMatchException#################1");
         log.error("Request: " + request.getRequestURL() +"\n"+ " raised " + e);
         return mv;
     }
   
     
     @org.springframework.web.bind.annotation.ExceptionHandler(DataAccessException.class)
     public ModelAndView pwNotFoundExceptionHandler(HttpServletRequest request, DataAccessException e) {
        
        ModelAndView mv = new ModelAndView("/errorPage");
        
        mv.addObject("errorCode", -3);
        mv.addObject("errorMsg", e.getMessage());
         System.out.println("#####################PwNotFoundExceptionHandler###################1");
                 
         log.error("Request: " + request.getRequestURL() +"\n"+ " raised " + e);
         
         return mv;
     }
     /*ㅁㄴㅇㅁㄴㅇㅁㄴㅇㄴㅁㅇ*/
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request,Exception exception){ 
      
       ModelAndView mv = new ModelAndView("/errorPage"); 
       mv.addObject("exception", exception);
       System.out.println("#####################DefaultExceptionHandler###################");
       log.error("defaultExceptionHandler", exception);
    
    return mv;
    }
    
    @Around("execution(* kr.co.seoulit.account..*.*(..)) || execution(* kr.co.seoulit.account..*service.*.*(..)) || execution(* kr.co.seoulit.account..*mapper.*.*(..))")
      /*
       @Around 어노테이션은 타겟 메서드를 감싸서 특정 Advice를 실행한다는 의미이다. 위 코드의 Advice는 타겟 메서드가 실행된 시간을 측정하기 위한 로직을 구현하였다. 
        추가적으로 execution(* com.saelobi..*.EventService.*(..))가 의미하는 바는 com.saelobi 아래의 패키지 경로의 EventService 객체의 모든 메서드에 이 Aspect를 적용하겠다는 의미다.
        또한
      @Around("@annotation(PerLogging)") 이렇게 경로지정 방식말고 특정 어노테이션이 붙은 포인트에 해당 Aspect를 실행할 수 있는 기능도 제공한다
      출력 결과에서 @PerLogging 어노테이션이 붙은 메서드만 Aspect가 적용된 것을 볼 수 있다. */
      
      
      public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
         String type = "";
         String name = joinPoint.getSignature().getDeclaringTypeName();//--> 해당 메소드의 클래스이름을 반환//  ++joinpoint는 메소드
         if (name.indexOf("Controller") > -1) {//'Controller' 문자열이 포함되어 있으면
            type = "Controller  \t:  ";
         }
         else if (name.indexOf("Facade") > -1) {//'Facade' 문자열이 포함되어 있으면
            type = "ServiceFacadeImpl  \t:  ";
         }
         else if (name.indexOf("Service") > -1) {
            type = "ServiceImpl  \t:  ";
         }
         else if (name.indexOf("DAO") > -1) {
            type = "DAO  \t\t:  ";
         }
        System.out.println(type + name + "." + joinPoint.getSignature().getName() + "()호출🔥🔥🔥🔥🔥");
         log.info(type + name + "." + joinPoint.getSignature().getName() + "()"); //-info : 상태변경등과 같은 정보성 메시지를 나타냅니다. 
         Object obj = joinPoint.proceed();
        System.out.println(type + name + "." + joinPoint.getSignature().getName() + "()종료❄️❄️❄️❄️❄️");
         return obj;
         /*
          여기서는 Object를 리턴하도록 되어있는데 아주 중요하다.
                     왜냐하면 AOP는 메서드를 가로채서(Proxy) 앞이나 뒤에 필요한 공통로직을 한다고 했기 때문이다. --> 실행되던 객체 그자체를 의미함, xxxController의 xxxMethod이면 그거에 대한 Logging을 하고 재차 동작을 이어나갸야함
         즉 proceed()에서 정상적으로 메서드를 실행한 후 리턴 값을 주는데 가로채서 어떤 action을 한 후에 기존 리턴 값을 되돌려 주지 않으면 가로챈 AOP가 결과값을 변경한 것, 지워버린 것과 다름없다.
         위 메서드에서는 단순하게 앞/뒤로 추가 로깅을 찍어주고 기존 메서드가 실행될 수 있게 pjp.proceed();를 호출했다.
          */
         
      }
}