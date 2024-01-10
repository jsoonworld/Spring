package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.*;


public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(CLienBean.class, PrototypeBean.class);

        CLienBean clientBean1 = ac.getBean(CLienBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        CLienBean clientBean2 = ac.getBean(CLienBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);



    }

    @Scope("singleton")
    static class CLienBean{

        @Autowired
        private Provider<PrototypeBean> prototypeBeansProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeansProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

//    @Scope("singleton")
//    static class CLienBean{
//        private final PrototypeBean prototypeBean;
//
//        @Autowired
//        public CLienBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }
//    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
