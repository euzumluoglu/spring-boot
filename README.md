## Proje açıklaması

Proje Spring boot projesidir. H2 db kullanılmıştır ve başlangıç scripleri schema-all.sql içerisindedir.

Proje serviceCallJob batch'in Web Servis çağrımı sonucu aldığı değerleri JPA kullanarak dbye aktarmaktadır.

Batch 5 sn aralıkla çalışmaktadır. İşlemini bitirmeden yeni bir batch başlamamaktadır.

Unit test çalışmasında schema-test.sql scripti kullanılmaktadır.