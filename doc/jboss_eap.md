RedHat
-
+ 為一個Linux發行套件1995發行
+ 2006 收購開源軟體商JBoss後主導
  + 一樣支持贊助與相關的社群專案發展  => 2014後JBoss As後來更名為WildFly
  + 將社群專案進行整合與商品化 =>  Jboss eap
  + 以訂閱方案與技術支援

What is Container
-
+ 就像List,Set 這類Collection 就是List Set容器
+ 對Java程式而言,JVM是作業系統也是其容器
+ 持有,保存,管理物件的群集
+ 管理就要負責物件的生命週期

Apache(Apache HTTP Server)
-
+ Apache 是全球最廣泛的http服務器

Tomcat
-
+ Tomcat可以運行 jsp/servlet
+ 有一般的Http容器性能一般所以會跟Apache放在一起提高性能 => Apache Tomcat

WildFly(Jboss As)
- 
+ 集合Web容器,Jsp Servlet容器
+ EJB容器應用服務器 WebLogin ,WebSphere
![](.jboss_eap_images/f28b4d5a.png)


JBoss EAP 7 (JBoss Enterprise Application Platform)
-
+ Jakarta EE 規格JavaEE皆可部屬的中間件平台
+ 集成WildFly中間件平台 => 可用性,消息系統
+ 模塊化結構，需要才啟用 => 提高啟動速度
  + 管理控制台
  + 管理Cli => 減少編輯XML自動化任務
+ 模式
  + 獨立服務器以單個服務器運行
  + 受管服務器以單個控制點管理多個實例




