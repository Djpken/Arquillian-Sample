## Arquillian介紹

+ Java EE測試的開源框架
+ 簡化單元,整合,功能測試
+ 提供容器化測試以便在不同容器測試(WildFly ,Tomcat ,GlassFish)
+ 支援不同工具並用(JUnit,TestNg)

#### Arquillian JUnit

+ Arquillian
    + 約定 => 告訴JUnit測試環境:
        + @RunWith(Arquillian.class) Junit3,4
        + @ExtendWith(ArquillianExtension.class) Junit5
        + @Deployment static method and this method return ShrinkWrap archive
        + At least one method with @Test annotation
+ ShrinkWrapApi
    + Purpose:
        + 與Classpath下的環境分隔開來
        + 專注於要測試的類
        + 更簡單測試Java EE企業部屬需要
    + Action:
        + Java Api create jar,war,ear
+ Container Adapter
    + Purpose:
      + 容器測試
    + Action:
      + 根據test classpath 中提供的容器使用 => 要導入配置的容器
      + 有分運行環境(嵌入式容器) ,客戶端(遠程容器),真實的容器
      + 根據要注入的實例 addClasses(.class)
      + 可以透過Maven profile對依賴分組達成並使用命令行參數決定
      + 使用遠程容器可以達到零啟動
#### 整體運行順序
1. Arquillian負責打包
2. ShrinkWrap負責以歸檔型式部屬到容器
3. 最後反饋給Eclipse JUnit

#### Advance
+ 需要一個空beans.xml文件來激活CDI(EJB是自動激活)
+ basket 和 order repository 注入到测试用例中，测试它们之间的交互。我们将注入 EJB 接口，而不是实现类。EJB 容器会自动查找所用接口的实现。
+ 遠程容器是一個獨立的進程,使用容器的客戶端的部屬API

#### Main
+ 真實測試 Mock通常為我們的一個策略 Arquillian可以將想要的資源加入 
+ 集成測試 將想測試的代碼注入By ShrinkWrapApi