package com.example.data

import com.example.R
import com.example.model.BrushingStep
import com.example.model.DentalTip
import com.example.model.DentalTopic
import com.example.model.QuizQuestion

object DentalDataSource {

    val topics: List<DentalTopic> = listOf(
        DentalTopic(
            id = "mengenal_gigi",
            title = "Mengenal Gigi dan Mulut",
            category = "Dasar Anatomi",
            shortDescription = "Pelajari struktur unik gigi, lapisan email pelindung, dentin, pulpa saraf, dan jenis-jenis gigi.",
            drawableResId = R.drawable.img_anatomy_teeth,
            badgeText = "Struktur & Fungsi",
            overview = "Gigi dan mulut adalah pintu gerbang pencernaan manusia. Gigi terdiri dari mahkota yang terlihat di atas gusi dan akar yang tertanam kokoh di tulang rahang. Setiap bagian memiliki peran vital dalam mengunyah makanan, berbicara jelas, dan menjaga bentuk estetika wajah.",
            causes = listOf(
                "Keausan alami lapisan email seiring bertambahnya usia.",
                "Kebiasaan menggemeretakkan gigi (bruxism) saat tidur.",
                "Tekanan berlebih saat mengunyah benda keras seperti es batu atau permen keras."
            ),
            symptoms = listOf(
                "Lapisan email terkikis terlihat agak menguning karena dentin di bawahnya mulai tampak.",
                "Rasa ngilu saat gigi bersentuhan dengan makanan atau minuman dingin maupun panas.",
                "Tepi gigi tampak bergerigi atau retak halus."
            ),
            prevention = listOf(
                "Gunakan sikat gigi berbulu lembut (soft) agar tidak mengikis lapisan email gigi.",
                "Hindari menggunakan gigi sebagai alat membuka tutup botol atau kemasan plastik.",
                "Konsumsi makanan kaya kalsium, fosfor, dan vitamin D untuk menjaga kepadatan mineral gigi."
            ),
            care = listOf(
                "Gunakan pasta gigi berfluorida yang membantu proses remineralisasi email.",
                "Jika memiliki kebiasaan bruxism, konsultasikan penggunaan night guard pelindung gigi.",
                "Bilas mulut dengan air putih hangat setelah mengonsumsi makanan yang bersifat asam."
            ),
            whenToDentist = "Periksakan gigi secara teratur setiap 6 bulan sekali, atau segera jika merasakan ngilu mendadak atau gigi tampak retak.",
            funFact = "Lapisan email gigi adalah substansi paling keras di seluruh tubuh manusia, bahkan lebih keras daripada tulang rangka!"
        ),
        DentalTopic(
            id = "cara_sikat_gigi",
            title = "Cara Menyikat Gigi yang Benar",
            category = "Kebersihan Harian",
            shortDescription = "Teknik menyikat gigi bersudut 45 derajat yang efektif mengangkat plak tanpa melukai gusi.",
            drawableResId = R.drawable.img_brushing_guide,
            badgeText = "Teknik Standar",
            overview = "Menyikat gigi bukan sekadar menggosok keras. Teknik yang benar berfokus pada gerakan menyapu lembut yang menjangkau batas gusi dan sela-sela gigi, memastikan sisa makanan terangkat tanpa merusak jaringan gusi maupun mengikis email.",
            causes = listOf(
                "Teknik menggosok horizontal terlalu keras yang menyebabkan abrasi leher gigi.",
                "Penggunaan sikat gigi berbulu kaku (hard) yang melukai gusi.",
                "Durasi menyikat terburu-buru (kurang dari 2 menit) sehingga banyak area terlewat."
            ),
            symptoms = listOf(
                "Gusi berdarah atau terasa perih setelah menyikat gigi.",
                "Muncul celah takik (abrasi) di perbatasan gigi dan gusi.",
                "Mulut masih terasa kurang segar karena plak di gigi belakang belum terangkat."
            ),
            prevention = listOf(
                "Sikat gigi dua kali sehari: pagi setelah sarapan dan malam sebelum tidur.",
                "Posisikan sikat membentuk sudut 45 derajat mengarah ke perbatasan gusi dan gigi.",
                "Gunakan gerakan memutar kecil atau menyapu dari arah gusi ke mahkota gigi."
            ),
            care = listOf(
                "Sikat seluruh permukaan: bagian luar, bagian dalam, dan permukaan kunyah.",
                "Jangan lupa bersihkan lidah secara perlahan dari belakang ke depan.",
                "Ganti sikat gigi setiap 3 bulan sekali atau lebih cepat jika bulunya sudah mulai mekar."
            ),
            whenToDentist = "Konsultasikan ke dokter gigi jika gusi Anda selalu berdarah setiap kali menyikat gigi meskipun telah melakukannya dengan lembut.",
            funFact = "Menyikat gigi malam sebelum tidur adalah waktu paling krusial karena produksi air liur pembersih alami menurun drastis saat kita tidur."
        ),
        DentalTopic(
            id = "karies_gigi",
            title = "Karies Gigi (Gigi Berlubang)",
            category = "Penyakit Gigi",
            shortDescription = "Kenali proses terbentuknya lubang gigi dari asam bakteri serta cara mengatasinya sebelum sakit berdenyut.",
            drawableResId = R.drawable.img_hero_dental,
            badgeText = "Deteksi Dini",
            overview = "Karies gigi terjadi ketika bakteri di mulut memfermentasi gula dari sisa makanan dan memproduksi asam. Asam ini melarutkan mineral email gigi secara perlahan hingga membentuk lubang. Jika tidak ditangani, infeksi akan menembus ke pulpa yang berisi saraf.",
            causes = listOf(
                "Konsumsi makanan manis, lengket, dan asam secara berlebihan dan berulang kali.",
                "Sisa makanan yang tertinggal di sela-sela gigi tidak dibersihkan dengan baik.",
                "Kurangnya paparan fluorida yang berfungsi memperkuat struktur mineral email.",
                "Kondisi mulut kering (saliva sedikit) sehingga asam sulit dinetralkan secara alami."
            ),
            symptoms = listOf(
                "Bercak putih kapur (white spot) atau noda kecokelatan pada permukaan gigi.",
                "Ngilu saat mengonsumsi makanan/minuman manis, dingin, atau bersuhu panas.",
                "Nyeri tajam berdenyut tanpa pemicu (spontan), terutama di malam hari bila infeksi sudah mencapai pulpa saraf."
            ),
            prevention = listOf(
                "Batasi camilan manis di sela jam makan utama.",
                "Gunakan pasta gigi berfluorida minimal 1000-1450 ppm.",
                "Lakukan flossing setiap hari untuk membersihkan plak di sela-sela yang tak terjangkau bulu sikat."
            ),
            care = listOf(
                "Tambal gigi sesegera mungkin pada tahap awal sebelum lubang membesar.",
                "Perawatan saluran akar (root canal) jika karies sudah menembus ruang saraf pulpa.",
                "Bila lubang sudah terlalu hancur, dokter gigi mungkin menyarankan mahkota tiruan (crown)."
            ),
            whenToDentist = "Segera kunjungi dokter gigi jika melihat titik hitam, makanan sering tersangkut di lubang gigi, atau timbul rasa ngilu saat makan.",
            funFact = "Gigi berlubang pada tahap awal (white spot) sebenarnya masih bisa pulih (remineralisasi) jika kebersihan dijaga dan diberi fluorida!"
        ),
        DentalTopic(
            id = "plak_dan_karang",
            title = "Plak dan Karang Gigi",
            category = "Kebersihan Mulut",
            shortDescription = "Pahami perbedaan plak yang licin lengket dan karang gigi keras yang hanya bisa dihilangkan dengan scaling.",
            drawableResId = R.drawable.img_doctor_checkup,
            badgeText = "Perawatan Klinis",
            overview = "Plak adalah lapisan tipis, lengket, dan tak kasat mata yang terdiri dari kuman dan sisa makanan. Bila plak tidak disikat bersih dalam 24 hingga 48 jam, ia akan mengeras bersama mineral air liur menjadi kalkulus atau karang gigi yang keras dan berwarna kekuningan/kehitaman.",
            causes = listOf(
                "Jarang menyikat gigi atau teknik menyikat gigi yang kurang bersih.",
                "Tidak pernah menggunakan benang gigi (dental floss).",
                "Komposisi air liur yang kaya mineral kalsium dan fosfat mempercepat pengendapan."
            ),
            symptoms = listOf(
                "Endapan kasar berwarna putih kekuningan, cokelat, atau hitam di perbatasan gigi dan gusi.",
                "Bau mulut yang menetap (halitosis) meski sudah berkumur atau menyikat gigi.",
                "Gusi tampak kemerahan dan mudah berdarah saat tersentuh sikat gigi."
            ),
            prevention = listOf(
                "Sikat gigi secara rutin dan tepat waktu dua kali sehari selama minimal dua menit.",
                "Gunakan dental floss setiap malam untuk mengangkat plak di area proksimal antargigi.",
                "Kurangi merokok karena tembakau mempercepat pewarnaan dan pengerasan kalkulus."
            ),
            care = listOf(
                "Karang gigi tidak dapat hilang dengan sikat gigi rumahan biasa.",
                "Satu-satunya cara aman dan tuntas adalah pembersihan karang gigi profesional (scaling) oleh dokter gigi dengan getaran ultrasonik.",
                "Lakukan pemolesan gigi setelah scaling agar permukaan gigi licin kembali."
            ),
            whenToDentist = "Jadwalkan scaling rutin setiap 6 bulan sekali untuk mencegah radang gusi dan kerusakan tulang rahang.",
            funFact = "Karang gigi memiliki pori-pori mikroskopis yang menjadi rumah nyaman bagi miliaran bakteri pemicu penyakit gusi dan bau mulut."
        ),
        DentalTopic(
            id = "sariawan",
            title = "Sariawan (Stomatitis Aftosa)",
            category = "Kesehatan Jaringan Lunak",
            shortDescription = "Luka perih kecil pada bibir, lidah, atau gusi: penyebab, cara meredakan nyeri, dan tanda bahaya.",
            drawableResId = R.drawable.img_app_icon,
            badgeText = "Kenyamanan Mulut",
            overview = "Sariawan atau stomatitis aftosa rekuren adalah luka kecil berbentuk bulat atau oval dengan dasar berwarna putih/kekuningan dan tepi kemerahan. Meski berukuran kecil, sariawan sering kali menimbulkan rasa perih luar biasa saat makan, minum, maupun berbicara.",
            causes = listOf(
                "Trauma fisik, misalnya tergigit secara tidak sengaja saat makan atau gesekan kawat gigi (behel).",
                "Stres psikologis dan kurang tidur yang memicu penurunan daya tahan tubuh.",
                "Kekurangan zat gizi penting seperti zat besi, asam folat, dan vitamin B12.",
                "Sensitivitas terhadap bahan deterjen Sodium Lauryl Sulfate (SLS) pada pasta gigi tertentu."
            ),
            symptoms = listOf(
                "Luka cekung berukuran beberapa milimeter di bibir dalam, pipi dalam, dasar mulut, atau lidah.",
                "Sensasi perih menyengat saat terkena makanan asam, asin, atau pedas.",
                "Rasa tidak nyaman saat menggerakkan mulut untuk berbicara."
            ),
            prevention = listOf(
                "Kunyah makanan dengan perlahan dan berhati-hati agar pipi dan lidah tidak tergigit.",
                "Gunakan lilin ortodontik (orthodontic wax) jika memakai behel agar kawat tidak melukai mukosa.",
                "Penuhi asupan gizi seimbang dari buah, sayuran, dan protein hewani.",
                "Kelola stres dengan istirahat yang cukup dan berolahraga teratur."
            ),
            care = listOf(
                "Hindari makanan pedas, asam, panas, atau bertekstur tajam/keras selama sariawan.",
                "Bilas mulut dengan larutan air garam hangat atau obat kumur antiseptik tanpa alkohol.",
                "Oleskan gel atau salep pereda sariawan yang mengandung zat pelindung mukosa."
            ),
            whenToDentist = "Segera konsultasi ke dokter jika sariawan tidak sembuh dalam 2 minggu, berdiameter lebih dari 1 cm, atau disertai demam tinggi.",
            funFact = "Sariawan bukanlah penyakit menular; kondisi ini murni reaksi peradangan lokal jaringan lunak mulut."
        ),
        DentalTopic(
            id = "kesehatan_gusi",
            title = "Kesehatan Gusi (Gingivitis & Periodontitis)",
            category = "Penyakit Jaringan Penyangga",
            shortDescription = "Lindungi pondasi gigi Anda. Kenali tanda radang gusi sebelum berlanjut menjadi gigi goyang.",
            drawableResId = R.drawable.img_brushing_guide,
            badgeText = "Pondasi Gigi",
            overview = "Gusi yang sehat berwarna merah muda merona, kenyal, melekat erat pada leher gigi, dan tidak berdarah saat disikat. Jika plak dibiarkan, gusi mengalami peradangan (gingivitis). Bila berlanjut, infeksi dapat merusak tulang penyangga gigi (periodontitis) hingga gigi menjadi goyang.",
            causes = listOf(
                "Akumulasi plak dan karang gigi yang menempel lama di bawah garis gusi.",
                "Kebiasaan merokok yang merusak sirkulasi darah mikro di jaringan gusi.",
                "Penyakit sistemik seperti diabetes melitus yang tidak terkontrol.",
                "Perubahan hormonal pada masa pubertas, kehamilan, atau menopause."
            ),
            symptoms = listOf(
                "Gusi tampak memerah, bengkak, dan mengkilap.",
                "Darah keluar saat menyikat gigi atau saat menggigit makanan keras seperti apel.",
                "Gusi tampak merosot sehingga leher gigi terlihat lebih panjang.",
                "Gigi terasa mulai goyang atau renggang satu sama lain."
            ),
            prevention = listOf(
                "Bersihkan garis gusi secara lembut setiap hari dengan bulu sikat yang halus.",
                "Lakukan flossing rutin untuk mengangkat bakteri subgingiva di sela gigi.",
                "Hentikan kebiasaan merokok untuk mempercepat pemulihan jaringan gusi."
            ),
            care = listOf(
                "Scaling untuk membersihkan racun kuman di permukaan akar gigi.",
                "Pemeriksaan kedalaman kantung gusi (periodontal pocket) oleh dokter gigi spesialis.",
                "Terapi obat kumur antibakteri chlorhexidine sesuai instruksi dokter gigi."
            ),
            whenToDentist = "Periksakan gusi bila sering berdarah tanpa sebab jelas, muncul nanah di sela gusi, atau gigi terasa bergerak saat mengunyah.",
            funFact = "Penyakit periodontitis adalah penyebab utama hilangnya gigi pada orang dewasa dan lansia, mengalahkan faktor gigi berlubang!"
        ),
        DentalTopic(
            id = "makanan_dan_minuman",
            title = "Makanan & Minuman untuk Gigi",
            category = "Gizi & Nutrisi",
            shortDescription = "Pilihan nutrisi sahabat gigi: kalsium, air putih, dan buah renyah vs makanan musuh gigi.",
            drawableResId = R.drawable.img_hero_dental,
            badgeText = "Nutrisi Sahabat",
            overview = "Apa yang kita makan berdampak langsung pada kekuatan enamel dan ekosistem air liur kita. Memilih makanan kaya mineral dan merangsang produksi saliva membantu gigi mempertahankan lapisan pelindungnya dari gempuran asam sisa makanan.",
            causes = listOf(
                "Kebiasaan sering menyeruput minuman manis atau bersoda dalam durasi lama.",
                "Sering mengonsumsi makanan manis lengket yang menempel kuat di ceruk gigi.",
                "Kurang minum air putih yang menyebabkan kondisi mulut menjadi kering dan asam."
            ),
            symptoms = listOf(
                "Rasa lengket di gigi yang sulit hilang setelah makan.",
                "Peningkatan pembentukan plak putih di sekeliling gigi.",
                "Email gigi mulai tampak kusam atau rapuh."
            ),
            prevention = listOf(
                "Konsumsi susu, keju, dan yogurt yang kaya kalsium dan kasein pembentuk mineral enamel.",
                "Perbanyak sayuran hijau seperti brokoli dan bayam yang kaya vitamin dan mineral.",
                "Kudap buah renyah seperti apel dan pir yang merangsang aliran air liur pembersih alami.",
                "Jadikan air putih sebagai minuman utama setiap hari."
            ),
            care = listOf(
                "Bilas mulut dengan berkumur air putih setelah meminum kopi, teh manis, atau jus asam.",
                "Tunggu 30 menit sebelum menyikat gigi setelah mengonsumsi makanan/minuman sangat asam agar enamel tidak terabrasi saat melunak.",
                "Gunakan sedotan ramah lingkungan jika menikmati minuman berasa untuk mengurangi kontak langsung dengan gigi depan."
            ),
            whenToDentist = "Tanyakan rekomendasi suplemen atau aplikasi fluorida topikal kepada dokter gigi jika enamel Anda tampak rentan berlubang.",
            funFact = "Keju mengandung kasein dan kalsium yang membantu menetralkan tingkat keasaman (pH) di dalam rongga mulut secara instan setelah makan."
        ),
        DentalTopic(
            id = "perawatan_lanjutan",
            title = "Perawatan Gigi & Mulut Lengkap",
            category = "Perawatan Komprehensif",
            shortDescription = "Maksimalkan senyum sehat dengan dental floss, pembersih lidah, dan obat kumur yang tepat.",
            drawableResId = R.drawable.img_brushing_guide,
            badgeText = "Higiene Maksimal",
            overview = "Sikat gigi hanya membersihkan sekitar 65% permukaan gigi. Untuk menjangkau 35% sisanya di sela-sela gigi dan permukaan lidah, kita membutuhkan alat pelengkap seperti benang gigi (dental floss), pembersih lidah (tongue scraper), dan obat kumur yang aman.",
            causes = listOf(
                "Mengabaikan sela-sela gigi sehingga plak tersembunyi memicu lubang interdental.",
                "Tidak membersihkan lidah yang menyimpan jutaan koloni bakteri penghasil gas belerang bau mulut.",
                "Penggunaan obat kumur beralkohol tinggi secara berlebihan yang mengeringkan mukosa mulut."
            ),
            symptoms = listOf(
                "Sering tersangkut serat daging di antara dua gigi yang sulit diambil.",
                "Lidah tampak putih berselaput tebal.",
                "Sensasi mulut kering atau terbakar setelah berkumur obat kumur yang terlalu keras."
            ),
            prevention = listOf(
                "Gunakan benang gigi (flossing) setidaknya satu kali sehari sebelum tidur.",
                "Gunakan alat pembersih lidah berbahan halus dari pangkal ke ujung lidah.",
                "Pilih obat kumur yang mengandung antibakteri non-alkohol dan fluorida."
            ),
            care = listOf(
                "Selipkan benang gigi dengan lembut membentuk huruf C mengikuti lekukan sisi gigi, jangan dihantamkan ke gusi.",
                "Bersihkan lidah 2-3 kali sapuan lembut, lalu bilas dengan air bersih.",
                "Bila memakai kawat gigi, gunakan sikat interdental khusus untuk membersihkan bracket."
            ),
            whenToDentist = "Minta peragaan teknik flossing yang benar saat kunjungan rutin Anda ke dokter gigi.",
            funFact = "Hampir 85% sumber bau mulut berasal dari tumpukan bakteri dan sel mati yang terjebak di celah papila permukaan lidah."
        ),
        DentalTopic(
            id = "pemeriksaan_dokter_gigi",
            title = "Pemeriksaan ke Dokter Gigi",
            category = "Pencegahan & Medis",
            shortDescription = "Kunjungan rutin setiap 6 bulan: investasi senyum indah tanpa rasa sakit dan tanpa rasa takut.",
            drawableResId = R.drawable.img_doctor_checkup,
            badgeText = "Rutin 6 Bulan",
            overview = "Pemeriksaan ke dokter gigi bukan hanya ketika gigi terasa sakit. Justru tujuan utama kunjungan rutin setiap 6 bulan adalah mendeteksi masalah sekecil apa pun sebelum berkembang menjadi penyakit berat yang memerlukan biaya dan penanganan lebih rumit.",
            causes = listOf(
                "Menunda kunjungan ke dokter gigi karena merasa giginya tidak ada yang sakit.",
                "Rasa cemas atau trauma masa lalu terhadap suara bor atau jarum suntik.",
                "Anggapan keliru bahwa scaling dapat menipiskan atau merusak gigi."
            ),
            symptoms = listOf(
                "Tiba-tiba mengalami sakit gigi hebat yang mengganggu tidur dan aktivitas kerja.",
                "Pipi bengkak akibat abses infeksi yang sudah menjalar ke ujung akar gigi.",
                "Gigi patah atau goyang parah karena keropos tak terdeteksi."
            ),
            prevention = listOf(
                "Buat jadwal pengingat pemeriksaan gigi setiap 6 bulan sekali untuk seluruh anggota keluarga.",
                "Ajak anak ke dokter gigi sejak gigi pertamanya tumbuh untuk membiasakan suasana klinik yang menyenangkan.",
                "Komunikasikan kekhawatiran Anda secara terbuka kepada dokter gigi."
            ),
            care = listOf(
                "Dokter gigi akan memeriksa seluruh jaringan mulut, mendeteksi karies awal, dan membersihkan karang gigi secara profesional.",
                "Klinik modern menggunakan instrumen mutakhir berdaya getar halus dan anestesi gel oles yang nyaman.",
                "Dapatkan saran perawatan yang disesuaikan secara personal dengan kondisi gigi Anda."
            ),
            whenToDentist = "Segera hubungi dokter gigi jika mengalami nyeri berdenyut, gusi membengkak, trauma benturan gigi, atau sariawan yang tak kunjung sembuh.",
            funFact = "Biaya pencegahan rutin setiap 6 bulan jauh lebih hemat hingga 80% dibandingkan biaya perawatan saluran akar dan mahkota gigi akibat gigi berlubang terlambat!"
        )
    )

    val brushingSteps: List<BrushingStep> = listOf(
        BrushingStep(
            stepNumber = 1,
            title = "Posisi Sikat 45 Derajat",
            instruction = "Posisikan bulu sikat gigi membentuk sudut 45 derajat mengarah ke perbatasan antara gusi dan gigi. Posisi ini memastikan bulu sikat dapat menjangkau sulkus gusi tempat plak paling sering menumpuk.",
            proTip = "Gunakan sikat berbulu lembut (soft bristles) dengan kepala sikat kecil agar mudah bermanuver hingga gigi geraham belakang.",
            targetArea = "Batas gusi rahang atas & rahang bawah",
            durationSeconds = 25
        ),
        BrushingStep(
            stepNumber = 2,
            title = "Gerakan Memutar Lembut",
            instruction = "Lakukan gerakan memutar kecil atau sapukan bulu sikat dari arah gusi menuju ke mahkota gigi (merah ke putih). Hindari menggosok maju-mundur secara keras karena dapat mengikis lapisan email di leher gigi.",
            proTip = "Genggam gagang sikat gigi seperti memegang pulpen agar tekanan yang diberikan tidak terlalu kuat dan melukai gusi.",
            targetArea = "Permukaan luar gigi depan & samping",
            durationSeconds = 25
        ),
        BrushingStep(
            stepNumber = 3,
            title = "Sisi Dalam Gigi (Lingual & Palatal)",
            instruction = "Miringkan sikat secara vertikal untuk membersihkan permukaan bagian dalam gigi depan atas dan bawah. Bersihkan juga bagian dalam gigi geraham dengan gerakan menyapu lembut ke arah luar.",
            proTip = "Sisi dalam gigi depan bawah adalah tempat paling cepat menumpuknya karang gigi karena berdekatan dengan muara kelenjar air liur.",
            targetArea = "Sisi dalam gigi menghadap lidah & langit-langit",
            durationSeconds = 25
        ),
        BrushingStep(
            stepNumber = 4,
            title = "Permukaan Kunyah (Oklusal)",
            instruction = "Letakkan bulu sikat mendatar di atas permukaan kunyah gigi geraham. Gerakkan sikat maju-mundur secara teratur dengan tekanan ringan agar bulu sikat masuk ke dalam ceruk dan lekukan kunyah.",
            proTip = "Ceruk (fissure) pada gigi geraham adalah tempat favorit sisa makanan bersarang dan paling rawan berlubang.",
            targetArea = "Permukaan kunyah geraham kanan & kiri",
            durationSeconds = 25
        ),
        BrushingStep(
            stepNumber = 5,
            title = "Membersihkan Permukaan Lidah",
            instruction = "Setelah seluruh gigi bersih, bersihkan permukaan lidah secara perlahan dari pangkal ke arah ujung lidah. Ini membantu menyingkirkan lapisan bakteri dan sel mati yang menjadi penyebab utama bau mulut.",
            proTip = "Gunakan bagian belakang kepala sikat gigi yang bertekstur karet atau gunakan alat pembersih lidah khusus.",
            targetArea = "Permukaan punggung lidah",
            durationSeconds = 20
        )
    )

    val dentalTips: List<DentalTip> = listOf(
        DentalTip(
            id = "tip_1",
            title = "Sikat Gigi 2 Kali Sehari",
            summary = "Waktu paling ideal adalah pagi setelah sarapan dan malam tepat sebelum tidur.",
            category = "Harian",
            detail = "Menyikat gigi sebelum tidur sangat krusial karena saat tidur produksi air liur pembersih alami berkurang drastis, sehingga kuman sangat leluasa merusak gigi jika sisa makanan dibiarkan.",
            highlight = "Pagi setelah sarapan & malam sebelum tidur"
        ),
        DentalTip(
            id = "tip_2",
            title = "Gunakan Pasta Berfluorida",
            summary = "Fluorida mengikat mineral gigi dan membuatnya jauh lebih tahan terhadap serangan asam kuman.",
            category = "Alat",
            detail = "Untuk orang dewasa gunakan sebesar kacang polong (pea-sized). Jangan langsung membilas mulut berkali-kali setelah menyikat gigi; cukup ludahkan busanya agar lapisan pelindung fluorida tetap bekerja di email gigi.",
            highlight = "Cukup ludahkan sisa busa, hindari kumur berlebih"
        ),
        DentalTip(
            id = "tip_3",
            title = "Bersihkan Sela Gigi (Dental Floss)",
            summary = "Bulu sikat tidak dapat menjangkau titik kontak antargigi tempat 35% sisa makanan bersarang.",
            category = "Alat",
            detail = "Gunakan benang gigi setiap malam. Selipkan perlahan mengikuti lekukan sisi gigi agar tidak mencederai papila gusi di sela-sela gigi Anda.",
            highlight = "Menjangkau 35% area tersembunyi"
        ),
        DentalTip(
            id = "tip_4",
            title = "Batasi Makanan & Minuman Manis",
            summary = "Gula adalah bahan bakar utama bagi bakteri mulut untuk memproduksi asam perusak email.",
            category = "Nutrisi",
            detail = "Bila ingin menikmati camilan manis, sebaiknya dikonsumsi bersamaan dengan jam makan utama, bukan dicicil sepanjang hari agar gigi tidak terpapar asam terus-menerus.",
            highlight = "Kurangi frekuensi ngemil manis di sela waktu makan"
        ),
        DentalTip(
            id = "tip_5",
            title = "Perbanyak Minum Air Putih",
            summary = "Air putih membilas sisa makanan secara alami dan menstimulasi produksi air liur sehat.",
            category = "Nutrisi",
            detail = "Minum segelas air putih setelah makan membantu menetralkan tingkat keasaman di mulut dan mencegah timbulnya mulut kering (xerostomia).",
            highlight = "Minimal 8 gelas per hari untuk mulut lembap terlindungi"
        ),
        DentalTip(
            id = "tip_6",
            title = "Periksa Gigi Rutin Setiap 6 Bulan",
            summary = "Deteksi dini karang gigi dan karies awal sebelum menimbulkan rasa sakit dan pembengkakan.",
            category = "Pencegahan",
            detail = "Pembersihan karang gigi (scaling) ultrasonik tidak merusak gigi, melainkan membuang sarang kuman berbahaya perusak tulang rahang.",
            highlight = "Rutin 2 kali setahun demi senyum seumur hidup"
        ),
        DentalTip(
            id = "tip_7",
            title = "Ganti Sikat Gigi Setiap 3 Bulan",
            summary = "Bulu sikat yang sudah mekar kehilangan daya bersihnya dan dapat menggores gusi.",
            category = "Alat",
            detail = "Ganti juga sikat gigi Anda lebih awal setelah Anda baru saja sembuh dari sakit flu atau radang tenggorokan untuk menghindari infeksi berulang.",
            highlight = "Maksimal 3 bulan pemakaian atau saat bulu sikat mulai mekar"
        ),
        DentalTip(
            id = "tip_8",
            title = "Bersihkan Permukaan Lidah",
            summary = "85% bakteri penyebab aroma tidak sedap (halitosis) bersemayam di celah permukaan lidah.",
            category = "Harian",
            detail = "Lakukan sapuan lembut dari pangkal ke arah ujung lidah setiap kali selesai menyikat gigi untuk nafas segar sepanjang hari.",
            highlight = "Nafas segar dan terbebas dari penumpukan bakteri"
        )
    )

    val quizQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            question = "Berapa lama durasi minimal yang dianjurkan dokter gigi untuk menyikat gigi?",
            options = listOf(
                "30 detik",
                "1 menit",
                "2 menit",
                "5 menit"
            ),
            correctOptionIndex = 2,
            explanation = "Durasi yang direkomendasikan adalah minimal 2 menit (sekitar 30 detik untuk setiap kuadran mulut) agar seluruh permukaan gigi dan batas gusi bersih optimal."
        ),
        QuizQuestion(
            id = 2,
            question = "Kapan waktu yang paling tepat dan penting untuk menyikat gigi?",
            options = listOf(
                "Hanya saat mandi sore",
                "Pagi setelah sarapan dan malam sebelum tidur",
                "Segera sebelum sarapan pagi saja",
                "Hanya saat merasa mulut terasa kotor"
            ),
            correctOptionIndex = 1,
            explanation = "Waktu terbaik adalah pagi setelah sarapan (agar sisa makanan tidak menempel seharian) dan malam sebelum tidur (karena saliva pelindung berkurang saat tidur)."
        ),
        QuizQuestion(
            id = 3,
            question = "Berapa sudut kemiringan bulu sikat gigi yang dianjurkan saat membersihkan batas gusi?",
            options = listOf(
                "15 derajat",
                "45 derajat",
                "90 derajat tegak lurus",
                "180 derajat mendatar"
            ),
            correctOptionIndex = 1,
            explanation = "Sudut 45 derajat ke arah perbatasan gusi memungkinkan bulu sikat membersihkan celah gusi (sulkus) tempat menumpuknya plak tanpa melukai jaringan lunak."
        ),
        QuizQuestion(
            id = 4,
            question = "Apakah karang gigi (kalkulus) yang sudah keras bisa hilang hanya dengan sikat gigi biasa?",
            options = listOf(
                "Bisa, jika disikat sangat keras",
                "Bisa, jika menggunakan pasta gigi pemutih",
                "Tidak bisa, harus dibersihkan dokter gigi dengan alat scaling",
                "Bisa, jika berkumur dengan air perasan lemon"
            ),
            correctOptionIndex = 2,
            explanation = "Karang gigi adalah plak yang telah termineralisasi dan mengeras. Karang gigi hanya bisa dihilangkan secara aman oleh dokter gigi dengan alat ultrasonik scaling."
        ),
        QuizQuestion(
            id = 5,
            question = "Bahan apa pada pasta gigi yang terbukti secara klinis memperkuat email dan mencegah gigi berlubang?",
            options = listOf(
                "Fluorida",
                "Gula pasir",
                "Pewarna makanan",
                "Alkohol"
            ),
            correctOptionIndex = 0,
            explanation = "Fluorida berperan membantu remineralisasi lapisan email gigi sehingga lebih tahan terhadap asam yang diproduksi oleh bakteri mulut."
        ),
        QuizQuestion(
            id = 6,
            question = "Berapa bulan sekali sebaiknya kita memeriksakan kesehatan gigi ke dokter gigi?",
            options = listOf(
                "Setiap 1 bulan sekali",
                "Setiap 6 bulan sekali",
                "Setiap 5 tahun sekali",
                "Hanya saat gigi terasa sakit"
            ),
            correctOptionIndex = 1,
            explanation = "Kunjungan rutin setiap 6 bulan dianjurkan untuk pembersihan karang gigi dan mendeteksi karies dini sebelum menimbulkan rasa sakit berdenyut."
        ),
        QuizQuestion(
            id = 7,
            question = "Apa fungsi utama benang gigi (dental floss)?",
            options = listOf(
                "Memutihkan seluruh gigi",
                "Membersihkan sisa makanan dan plak di sela-sela gigi",
                "Menggantikan peran sikat gigi seutuhnya",
                "Mengurangi rasa ngilu gigi"
            ),
            correctOptionIndex = 1,
            explanation = "Benang gigi membersihkan permukaan antargigi yang sempit, yang menyumbang sekitar 35% area gigi yang tidak terjangkau oleh bulu sikat."
        ),
        QuizQuestion(
            id = 8,
            question = "Apa tindakan yang sebaiknya dihindari saat sedang mengalami sariawan?",
            options = listOf(
                "Minum banyak air putih",
                "Mengonsumsi makanan yang sangat pedas, panas, dan asam",
                "Menjaga kebersihan mulut",
                "Istirahat yang cukup"
            ),
            correctOptionIndex = 1,
            explanation = "Makanan pedas, sangat panas, dan asam akan mengiritasi ujung saraf luka sariawan dan memperparah rasa perih serta memperlambat pemulihan."
        )
    )

    val quickDentalFacts = listOf(
        "Email gigi adalah zat paling keras di tubuh manusia, mengungguli kekuatan tulang.",
        "Air liur (saliva) adalah pertahanan alami terbaik mulut yang mengandung enzim antibakteri.",
        "Menyikat gigi terlalu keras tidak membuat gigi lebih bersih, malah dapat menyebabkan abrasi leher gigi.",
        "Mengganti sikat gigi setiap 3 bulan mencegah transfer kembali kuman yang bersarang pada bulu sikat yang rusak.",
        "Penyakit gusi tanpa pengobatan dapat meningkatkan risiko komplikasi pada penderita diabetes."
    )
}
