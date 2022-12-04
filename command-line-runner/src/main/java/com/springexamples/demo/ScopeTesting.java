package com.springexamples.demo;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackageClasses = SingletonBean.class)
public class ScopeTesting {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ScopeTesting.class);

    SingletonBean bean = context.getBean(SingletonBean.class);

    bean.print();
  }
}

@Component
class SingletonBean {

  @Lookup
  private PrototypeBean getPrototypeBean(){
    return null;
  }

  public void print() {
    System.out.println(getPrototypeBean().hashCode());
  }
}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class PrototypeBean {

  public void print() {
    System.out.println(this.hashCode());
  }
}
