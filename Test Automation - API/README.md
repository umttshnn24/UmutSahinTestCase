# 🐾 PetStore API Test Automation Project

Bu proje, [Swagger PetStore](https://petstore.swagger.io/) servisi üzerinde bulunan `pet` endpoint’leri ile **CRUD (Create, Read, Update, Delete)** işlemlerini test etmek için oluşturulmuştur. Projede **Rest-Assured**, **TestNG**, **Java**, **Maven** teknolojileri kullanılmıştır.

---

## ✅ Test Kapsamı

Aşağıdaki işlemler hem **pozitif** hem de **negatif senaryolarla** test edilmiştir:

1. **Create Pet (POST)**
    - Geçerli bir pet nesnesi göndererek oluşturma
    - Eksik veya hatalı veri ile gönderim (negatif)

2. **Get Pet by ID (GET)**
    - Var olan pet ID’si ile sorgulama
    - Geçersiz veya silinmiş pet ID’si ile sorgulama (negatif)

3. **Update Pet (PUT)**
    - Mevcut bir pet’in bilgilerini güncelleme
    - Geçersiz pet ID veya eksik alanlarla güncelleme (negatif)

4. **Delete Pet (DELETE)**
    - Var olan pet’i silme
    - Silinmiş veya geçersiz ID ile silme işlemi (negatif)

---

## 🔧 Kullanılan Teknolojiler

- **Java 11+**
- **Maven**
- **Rest-Assured** (API testleri)
- **TestNG** (Test framework)
- **Log4j** (Loglama)
- **Allure** (Raporlama)

---
## ⚙️ Yapılandırma

`config.properties` dosyasında temel ayarlar yer alır:

---
## 🚀 Testleri Çalıştırma
- **Maven ile testleri çalıştır**

    mvn clean test

- **Allure raporunu oluştur ve göster**

    allure serve allure-results

---
## 📊 Raporlama
Başarısız adımlar için loglar ve detaylar Allure raporlarına eklenir.

Log4j ile test sürecinde detaylı log takibi yapılır.


📧 Bu dosya, teknik değerlendirme amacıyla hazırlanmıştır.
Test senaryosu proje gereksinimlerine göre uyarlanabilir veya genişletilebilir.