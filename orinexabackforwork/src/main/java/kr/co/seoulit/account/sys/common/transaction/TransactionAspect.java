package kr.co.seoulit.account.sys.common.transaction;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;


import java.util.Collections;

@Configuration
public class TransactionAspect {
    private static final String AOP_TRANSACTION_METHOD_NAME = "*"; //이름.
    private static final String AOP_TRANSACTION_EXPRESSION = "execution(* kr.co.seoulit..service.*.*(..) ) ";   //포인트컷. 이렇게 설정 값을 상수로 선언함.

    @Autowired  //이 타입을 자동으로 찾아줘서 DI 시켜줌.
    private PlatformTransactionManager transactionManager;

   /*
   @SuppressWarnings
   1. all : 모든 경고를 억제
   2. cast : 캐스트 연산자 관련 경고 억제
   3. dep-ann : 사용하지 말아야 할 주석 관련 경고 억제
   4. deprecation : 사용하지 말아야 할 메소드 관련 경고 억제
   5. fallthrough : switch문에서의 break 누락 관련 경고 억제
   6. finally : 반환하지 않는 finally 블럭 관련 경고 억제
   7. null : null 분석 관련 경고 억제
   8. rawtypes : 제네릭을 사용하는 클래스 매개 변수가 불특정일 때의 경고 억제
   9. unchecked : 검증되지 않은 연산자 관련 경고 억제
   10. unused : 사용하지 않는 코드 관련 경고 억제
       */
    @SuppressWarnings("deprecation")
    @Bean
    public TransactionInterceptor transactionAdvice(){
        MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);  //메서드 명-->모든메서드에대해서 적용하겠다
        transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(DataAccessException.class))); //트랜잭션에서 롤백을 하는 롤(rule)을 설정.
        source.setTransactionAttribute(transactionAttribute);
        return new TransactionInterceptor(transactionManager, source); //TransactionInterceptor가 아니라 Advice다?? --트랜잭션매니저도 주고 소스객체도 주었다
    }

    @Bean
    public Advisor transactionAdviceAdvisor(){ //어드바이저 : 포인트컷과 연결시켜 줌.
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice()); //Advisor객체가 리턴된다
        //------------------------------------------->>>>>>>>>>>>>>>>>>>>>>>트랜잭션관련된 처리부분이다 현업에서는 만들일이 없다 보면이해는할수있어야한다
    }
}
