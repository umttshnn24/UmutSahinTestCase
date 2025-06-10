# n11.com Arama Modülü Yük Testi

Bu proje, [n11.com](https://www.n11.com) üzerinde **arama modülünün** performansını değerlendirmek için basit bir JMeter yük testi senaryosu içermektedir.

## 🎯 Amaç

Kullanıcının ürün araması yaptığı **üst menüdeki arama fonksiyonu**nun yük altında nasıl davrandığını test etmek (örnek: "telefon" araması).

## 🧪 Test Senaryosu

- `https://www.n11.com/` adresi açılır
- Arama kutusu üzerinden `q=telefon` sorgusu gönderilir
- Arama sonuç sayfasının yanıt süresi ve davranışı ölçülür
- Tek kullanıcı ile test yapılır (isteğe bağlı ölçeklenebilir)

## 📄 Dahil Olan Dosyalar

- `n11-load-test.jmx`: Test senaryosunu içeren JMeter test planı.

## ⚙️ Nasıl Kullanılır

1. [Apache JMeter](https://jmeter.apache.org/download_jmeter.cgi) uygulamasını indirin ve kurun.
2. JMeter’ı açın.
3. Test planını yükleyin:
4. Yeşil **Başlat** butonuna tıklayarak testi çalıştırın.
5. Sonuçları şu bileşenlerle görüntüleyebilirsiniz:
- View Results Tree (Sonuç Ağacı)
- Summary Report (Özet Rapor)
- Graph Results (Grafiksel Sonuçlar)

## 🧰 Kullanılan Bileşenler

- **Thread Group**: 1 kullanıcı simüle eder.
- **HTTP Request**: `https://www.n11.com/arama?q=telefon` adresine GET isteği gönderir.
- **HTTP Header Manager**: Tipik tarayıcı başlıklarını simüle eder.
- **View Results Tree** ve **Summary Report**: Yanıt verilerini ve metrikleri gösterir.

## 📊 Örnek Çıktılar

Toplanabilecek metrikler:
- Yanıt Süresi
- İşlem Hacmi (Throughput)
- Başarı Oranı
- Hata Kodları (varsa)

## 🔁 Geliştirme Önerileri

- Birden fazla eş zamanlı kullanıcı ile test yapılabilir.
- Kullanıcı düşünme süresini simüle etmek için zamanlayıcı eklenebilir.
- Arama terimi parametreleştirilebilir.
- İçerik doğrulama için assertion eklenebilir.

---

📧 Bu dosya, teknik değerlendirme amacıyla hazırlanmıştır.  
Test senaryosu proje gereksinimlerine göre uyarlanabilir veya genişletilebilir.
