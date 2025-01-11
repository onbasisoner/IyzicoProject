
Klasör yapısı:
-[Action](src/test/java/utils/helpers/Action.java) ve [Helper](src/test/java/utils/helpers/Helper.java) class ımda projede kullandığım fonksiyonlar yer alıyor. 
-[Page](src/test/java/pages/CommonPage.java) classımda bu fonksiyonları çağırıp steplere hazır hale getiriyorum.
-[Step](src/test/java/steps/CommonStep.java) classımda BDD adımlarım ve senaryo adımlarım yer alıyor.
-Son olarak [feature](src/test/resources/features/task.feature) dosyam ile test steplerini yazıyorum.

Driver yapısı:
-İki farklı driver ile hazırladım. Her tarayıcı için yeni bir dosya olusturabiliriz. Bu tarayıcılarımı [properties](src/test/resources/properties/env.properties) dosyamda tutuyorum.
-Driver yönetimimi [WebDrivers](src/test/java/utils/drivers/WebDrivers.java) class ı ile yapıyorum.
-Properties dosyamdaki variable larıma [PropertyManager](src/test/java/utils/helpers/PropertyManager.java) ile erişiyorum.
-Driver icin WebDriverManager kullandım, bu sayede sürekli webdriver dosyası indirmeme gerek kalmıyor.
-Headless koşum için de properties de bir alan kullanıyorum. Bu proje icin gerek yok tabi ama bulutta kosarken headless yapmam gerekiyor.
-Domaini de properties de tutuyorum. Değişebilir alanlar eklendikce properties kullanılabilir. 

Element ve test case yazımı:
-Elementlerim için [json](src/test/resources/elements/mainPage.json) dosyası kullanıyorum. Düzenli ve daha kolay ulasılabilir olmasını saglıyor.
-JSON dosyasına eklenecek elementler için [elementHelper](src/test/java/utils/helpers/elementHelper) classlarını kullanıyorum. Basitçe [elements](src/test/java/utils/helpers/elementHelper/Elements.java) dosyamda typeları alıyorum. 4 tane ekledim ama başka BY fonksiyonları da eklenebilir CSS selector gibi.
-[Helper](src/test/java/utils/helpers/Helper.java#L96) classım içindeki getBy fonksiyonum ile jsondaki elementlerimi projeye bağlıyorum.
-Okunabilirliği artırmak için de Test caselerde jsonda verdiğim isimleri kullanıyorum.

Test koşumu ve raporlar:
-Cucumber [testrunner](src/test/java/Runner/TestRunner.java) kullanıyorum.
-Cucumber ın kendi raporu var, publish command i ile bunu sağlayabiliyorum.
-Ayrıca HTML raporu için plugin ekledim. Raporlar şuraya geliyor. ( [Rapor](target/cucumber-report.html) )
-Her koşum sonrası son kaldığı sayfanın ekran görüntüsünü alıyorum. ( [Screenshot klasörü](target/ss) )
-Tag ekledim ama tek feature olduğu için çok bir özellik katmıyor proje özelinde.

Mümkün olduğunca comment yazmaya çalıştım.

