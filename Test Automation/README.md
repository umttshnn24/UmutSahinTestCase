## 📘 Insider Web UI Test Otomasyon Projesi

Bu proje, [Insider](https://useinsider.com/) web sitesinin belirli sayfalarının test otomasyonunu saglamak amaciyla gelistirilmistir. Projede Java, Selenium WebDriver ve JUnit kullanilmistir. Yapı Page Object Model (POM) tasarim desenine uygundur.

---

### ✅ Test Senaryolari

1. **Ana Sayfa Kontrolü**
   `https://useinsider.com/` sitesine gidilir ve anasayfa kontrol edilir.

2. **Kariyer Sayfası ve Icerik Kontrolü**
   Navigasyon menüsünden "Company > Careers" secilir.

   * Locations, Teams ve Life at Insider bloklarının goruntulendigi dogrulanir.

3. **Pozisyon Filtreleme**
   `https://useinsider.com/careers/quality-assurance/` adresine gidilir.

   * "See all QA jobs" butonuna tiklanir.
   * Lokasyon: "Istanbul, Turkey" ve Departman: "Quality Assurance" olarak filtre uygulanir.
   * Ilgili pozisyonlar listelenir.

4. **Pozisyon Bilgi Doğrulama**

   * Her ilanin:

      * Pozisyon adında "Quality Assurance"
      * Departman alanında "Quality Assurance"
      * Lokasyon alanında "Istanbul, Turkey" icerigi kontrol edilir.

5. **Ilana Giris ve Yonlendirme**

   * "View Role" butonuna tiklanir.
   * Kullanıcının Lever basvuru formu sayfasina yonlendirildigi dogrulanir.

---

### 🔧 Kullanilan Teknolojiler

* Java 11
* Selenium WebDriver
* JUnit 4.13.2
* Maven
* WebDriverManager
* Log4j
* Allure Reporting

---

### ⚙️ Proje Ozellikleri

* Tum testler Page Object Model (POM) mimarisine uygun yazilmistir.
* Testler Chrome ve Firefox tarayicilarda calistirilabilir.
* Tarayici secimi parametrik olarak degistirilebilir (`configuration.properties`).
* Hatalarda otomatik ekran goruntusu alinip Allure raporlarina eklenir.

---

### 📆 Gereksinimler

* Java JDK 11+
* Maven
* Git
* Chrome / Firefox
* Allure (raporlar icin)

---

### 🚀 Projeyi Calistirma

```bash
# Testleri calistir
mvn clean test

# Allure raporu olustur
allure serve allure-results
```

---

### 📊 Raporlama

* Allure ile detaylı test raporu olusturulur.
* Her test adimi icin ekran goruntusu destegi vardir (basarisizlik durumunda).
---

📧 Bu dosya, teknik değerlendirme amacıyla hazırlanmıştır.  
Test senaryosu proje gereksinimlerine göre uyarlanabilir veya genişletilebilir.
