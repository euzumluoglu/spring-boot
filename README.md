## Proje açıklaması

Proje Spring boot projesidir. H2 db kullanılmıştır ve başlangıç scripleri schema-all.sql içerisindedir.

Başlatmak için com.shopinle.config içerisindeki Applicaiton.java tetiklenmelidir.

Proje serviceCallJob batch'in Web Servis çağrımı sonucu aldığı değerleri JPA kullanarak dbye aktarmaktadır.

Batch 5 sn aralıkla çalışmaktadır. İşlemini bitirmeden yeni bir batch başlamamaktadır.

Unit test çalışmasında schema-test.sql scripti kullanılmaktadır.

# Maven

Aktif profil clean-build olarak tanımlıdır.

WSDL çıktısı için generate profili kullanılmalıdır. WSDL çıktısını tüketilen web servisinin değişmesi 
durumunda ihtiyaç olacaktır.