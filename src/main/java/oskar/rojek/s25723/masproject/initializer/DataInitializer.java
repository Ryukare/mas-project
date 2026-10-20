package oskar.rojek.s25723.masproject.initializer;

import oskar.rojek.s25723.masproject.model.*;
import oskar.rojek.s25723.masproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final KrainaRepository krainaRepository;
    private final StarozytnaRuinaRepository starozytnaRuinaRepository;
    private final EkspedycjaRepository ekspedycjaRepository;

    private final RozpoznanieWTerenieRepository rozpoznanieWTerenieRepository;
    private final OdkrywcaRepository odkrywcaRepository;
    private final MagRepository magRepository;
    private final WojownikRepository wojownikRepository;
    private final ZwiadowcaRepository zwiadowcaRepository;
    private final TemplariuszRepository templariuszRepository;

    private final ProfesjaRepository profesjaRepository;
    private final PracaRepository pracaRepository;
    private final StarozytnyRepository starozytnyRepository;
    private final PrzekletyStarozytnyRepository przekletyStarozytnyRepository;
    private final PowietrznyStarozytnyRepository powietrznyStarozytnyRepository;

    private final BronRepository bronRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        if(krainaRepository.count() == 0 && starozytnaRuinaRepository.count() == 0 && ekspedycjaRepository.count() == 0 &&
                odkrywcaRepository.count() == 0 && profesjaRepository.count() == 0 && starozytnyRepository.count() == 0 &&
                bronRepository.count() == 0){

            StarozytnaRuina sr1 = StarozytnaRuina.builder()
                    .nazwa("Wulkan")
                    .wiek(2)
                    .poziomTrudnosci(3)
                    .wymaganaMoc(200)
                    .build();

            Starozytny str1 = Starozytny.builder()
                    .nazwa("Piekielny")
                    .opis("Prosto z piekieł")
                    .zycie(100)
                    .sila(100)
                    .odpornosc(100)
                    .umiejetnosci(Set.of("Ognista pięść"))
                    .ruina(sr1)
                    .build();

            Starozytny str4 = Starozytny.builder()
                    .nazwa("Armagedon")
                    .opis("Koniec jest blisko")
                    .zycie(1000)
                    .sila(1000)
                    .odpornosc(1000)
                    .umiejetnosci(Set.of("Pięść zagłady"))
                    .ruina(sr1)
                    .build();
            PrzekletyStarozytny przekStr2 = PrzekletyStarozytny.builder()
                    .mocKlatwy(1000)
                    .rozmiarAury(1000)
                    .starozytny(str4)
                    .build();

            PowietrznyStarozytny powStr2 = PowietrznyStarozytny.builder()
                    .predkoscLotu(1000)
                    .rozmiarSkrzydel(1000)
                    .starozytny(str4)
                    .build();

            StarozytnaRuina sr2 = StarozytnaRuina.builder()
                    .nazwa("Lodowy Grobowiec")
                    .wiek(2)
                    .poziomTrudnosci(2)
                    .wymaganaMoc(150)
                    .build();

            Starozytny str2 = Starozytny.builder()
                    .nazwa("Lodowy")
                    .opis("Prosto z lodowca")
                    .zycie(100)
                    .sila(100)
                    .odpornosc(100)
                    .umiejetnosci(Set.of("Lodowa pięść"))
                    .ruina(sr2)
                    .build();
            PrzekletyStarozytny przekStr1 = PrzekletyStarozytny.builder()
                    .mocKlatwy(100)
                    .rozmiarAury(100)
                    .starozytny(str2)
                    .build();

            StarozytnaRuina sr3 = StarozytnaRuina.builder()
                    .nazwa("Grobowiec Azteków")
                    .wiek(2)
                    .poziomTrudnosci(1)
                    .wymaganaMoc(125)
                    .build();

            Starozytny str3 = Starozytny.builder()
                    .nazwa("Powietrzny")
                    .opis("Prosto z niebios")
                    .zycie(100)
                    .sila(100)
                    .odpornosc(100)
                    .umiejetnosci(Set.of("Powietrzna pięść"))
                    .ruina(sr3)
                    .build();
            PowietrznyStarozytny powStr1 = PowietrznyStarozytny.builder()
                    .predkoscLotu(100)
                    .rozmiarSkrzydel(100)
                    .starozytny(str3)
                    .build();

            StarozytnaRuina sr4 = StarozytnaRuina.builder()
                    .nazwa("Leśne cmentarzysko")
                    .wiek(200)
                    .poziomTrudnosci(2)
                    .wymaganaMoc(300)
                    .build();

            Starozytny str5 = Starozytny.builder()
                    .nazwa("Zaklęty drzewiec")
                    .opis("Skrzypi aż uszy bolą")
                    .zycie(100)
                    .sila(100)
                    .odpornosc(100)
                    .umiejetnosci(Set.of("Podmuch liści"))
                    .ruina(sr4)
                    .build();

            StarozytnaRuina sr5 = StarozytnaRuina.builder()
                    .nazwa("Świątynia Shad'rai")
                    .wiek(2000)
                    .poziomTrudnosci(4)
                    .wymaganaMoc(450)
                    .build();

            Starozytny str6 = Starozytny.builder()
                    .nazwa("Starożytny Shad'rai")
                    .opis("Starożytny kapłan broniący grobowca")
                    .zycie(10000)
                    .sila(2100)
                    .odpornosc(170)
                    .umiejetnosci(Set.of("Przeznaczenie"))
                    .ruina(sr5)
                    .build();

            StarozytnaRuina sr6 = StarozytnaRuina.builder()
                    .nazwa("Zamrożony pałac")
                    .wiek(2000)
                    .poziomTrudnosci(3)
                    .wymaganaMoc(500)
                    .build();

            Starozytny str7 = Starozytny.builder()
                    .nazwa("Upadły król")
                    .opis("Niegdyś wysławiany. Dziś potępkiany")
                    .zycie(10000)
                    .sila(2100)
                    .odpornosc(170)
                    .umiejetnosci(Set.of("Ostrze mrozu"))
                    .ruina(sr6)
                    .build();

            Kraina kr1 = Kraina.builder()
                    .nazwa("Ognista Kraina")
                    .dominujacyZywiol("Ogień")
                    .wielkosc(123)
                    .populacja(1000)
                    .ruiny(Set.of(sr1))
                    .build();
            Kraina kr2 = Kraina.builder()
                    .nazwa("Lodowa Kraina")
                    .dominujacyZywiol("Lód")
                    .wielkosc(2310)
                    .populacja(100)
                    .ruiny(Set.of(sr2, sr6))
                    .build();
            Kraina kr3 = Kraina.builder()
                    .nazwa("Wielka Puszcza")
                    .dominujacyZywiol("Natura")
                    .wielkosc(12345)
                    .populacja(5431)
                    .ruiny(Set.of(sr3, sr4, sr5))
                    .build();

            Profesja pr1 = Profesja.builder()
                    .nazwa("Kowal")
                    .opis("Wykuwa bronie")
                    .build();
            Profesja pr2 = Profesja.builder()
                    .nazwa("Płatnerz")
                    .opis("Wykuwa zbroje")
                    .build();
            Profesja pr3 = Profesja.builder()
                    .nazwa("Krawiec")
                    .opis("Szyje ubrania")
                    .build();
            Profesja pr4 = Profesja.builder()
                    .nazwa("Farmer")
                    .opis("Hoduje Jedzenie")
                    .build();

            Mag mg1 = Mag.builder()
                    .nazwa("Aeloria")
                    .rasa("Elf")
                    .miejsceUrodzenia("Eldoria")
                    .doswiadczenie(110)
                    .sila(10)
                    .zrecznosc(15)
                    .inteligencja(37)
                    .mana(25)
                    .znaneCzary(Set.of("Fireball", "Ice Spear"))
                    .nazwaAkademii("Eldorian Academy")
                    .build();

            Bron br1 = Bron.builder()
                    .nazwa("Różdżka wieków")
                    .opis("Wieczna różdżka")
                    .typ("Różdżka")
                    .rzadkosc("Epicka")
                    .wymaganaSila(1)
                    .wymaganaZrecznosc(4)
                    .wymaganaInteligencja(30)
                    .wlasciciel(mg1)
                    .build();

            Mag mg2 = Mag.builder()
                    .nazwa("Thalindra")
                    .rasa("Czlowiek")
                    .miejsceUrodzenia("Thornhaven")
                    .doswiadczenie(100)
                    .sila(5)
                    .zrecznosc(10)
                    .inteligencja(20)
                    .mana(100)
                    .znaneCzary(Set.of("Fireball", "Ice Spear"))
                    .nazwaAkademii("Eldorian Academy")
                    .build();

            Bron br2 = Bron.builder()
                    .nazwa("Kryształ Many")
                    .opis("Latający kryształ many")
                    .typ("Katalizator")
                    .rzadkosc("Rzadka")
                    .wymaganaSila(1)
                    .wymaganaZrecznosc(7)
                    .wymaganaInteligencja(15)
                    .wlasciciel(mg2)
                    .build();

            Wojownik woj1 = Wojownik.builder()
                    .nazwa("Lyra")
                    .rasa("Krasnolud")
                    .miejsceUrodzenia("Mystfall")
                    .doswiadczenie(15)
                    .sila(27)
                    .zrecznosc(15)
                    .inteligencja(5)
                    .obrona(30)
                    .preferowanyTypBroni("Miecz")
                    .build();

            Bron br3 = Bron.builder()
                    .nazwa("Runiczny Miecz")
                    .opis("Miecz zaklęty w runy")
                    .typ("Miecz")
                    .rzadkosc("Epicka")
                    .wymaganaSila(20)
                    .wymaganaZrecznosc(10)
                    .wymaganaInteligencja(2)
                    .wlasciciel(woj1)
                    .build();

            Wojownik woj2 = Wojownik.builder()
                    .nazwa("Draven")
                    .rasa("Czlowiek")
                    .miejsceUrodzenia("Azerith")
                    .doswiadczenie(107)
                    .sila(93)
                    .zrecznosc(50)
                    .inteligencja(2)
                    .obrona(10)
                    .preferowanyTypBroni("Topór")
                    .build();

            Bron br4 = Bron.builder()
                    .nazwa("Spiralny Topór")
                    .opis("Topór, którym można kręcić wokół własnej osi")
                    .typ("Topór")
                    .rzadkosc("Legendarna")
                    .wymaganaSila(50)
                    .wymaganaZrecznosc(20)
                    .wymaganaInteligencja(1)
                    .wlasciciel(woj2)
                    .build();

            Zwiadowca zw1 = Zwiadowca.builder()
                    .nazwa("Kaelithar")
                    .rasa("Elf")
                    .miejsceUrodzenia("Silverpeak")
                    .doswiadczenie(33)
                    .sila(12)
                    .zrecznosc(34)
                    .inteligencja(25)
                    .celnosc(36)
                    .poziomUmiejetnosciZwiadowczych(31)
                    .build();

            Bron br5 = Bron.builder()
                    .nazwa("Łuk z magicznej kory")
                    .opis("Łuk wykonany z bardzo potężnego drzewa")
                    .typ("Łuk")
                    .rzadkosc("Epicka")
                    .wymaganaSila(10)
                    .wymaganaZrecznosc(30)
                    .wymaganaInteligencja(15)
                    .wlasciciel(zw1)
                    .build();

            Zwiadowca zw2 = Zwiadowca.builder()
                    .nazwa("Eldric")
                    .rasa("Czlowiek")
                    .miejsceUrodzenia("Drakenridge")
                    .doswiadczenie(5)
                    .sila(5)
                    .zrecznosc(5)
                    .inteligencja(5)
                    .celnosc(5)
                    .poziomUmiejetnosciZwiadowczych(5)
                    .build();

            Bron br6 = Bron.builder()
                    .nazwa("Kusza ze stali")
                    .opis("Łuk wykonany z bardzo potężnego drzewa")
                    .typ("Kusza")
                    .rzadkosc("Zwykła")
                    .wymaganaSila(3)
                    .wymaganaZrecznosc(3)
                    .wymaganaInteligencja(3)
                    .wlasciciel(zw2)
                    .build();

            Templariusz tmp1 = Templariusz.builder()
                    .nazwa("Seraphina")
                    .rasa("Elf")
                    .miejsceUrodzenia("Celestia")
                    .doswiadczenie(135)
                    .sila(15)
                    .zrecznosc(70)
                    .inteligencja(103)
                    .mana(136)
                    .znaneCzary(Set.of("Holy Sphere", "Judgement"))
                    .nazwaAkademii("Eldorian Academy")
                    .obrona(100)
                    .preferowanyTypBroni("Kostur")
                    .build();

            Bron br7 = Bron.builder()
                    .nazwa("Ostateczny osąd")
                    .opis("Kostur, którego osądu nie przeżył nikt")
                    .typ("Kostur")
                    .rzadkosc("Legendarna")
                    .wymaganaSila(2)
                    .wymaganaZrecznosc(30)
                    .wymaganaInteligencja(50)
                    .wlasciciel(tmp1)
                    .build();

            Templariusz tmp2 = Templariusz.builder()
                    .nazwa("Vorathar")
                    .rasa("Krasnolud")
                    .miejsceUrodzenia("Shadowfen")
                    .doswiadczenie(120)
                    .sila(100)
                    .zrecznosc(20)
                    .inteligencja(20)
                    .mana(30)
                    .znaneCzary(Set.of("Fireball", "Ice Spear"))
                    .nazwaAkademii("Eldorian Academy")
                    .obrona(50)
                    .preferowanyTypBroni("Młot")
                    .build();

            Bron br8 = Bron.builder()
                    .nazwa("Młot przeznaczenia")
                    .opis("Młot przeznaczony dla wybrańców")
                    .typ("Młot")
                    .rzadkosc("Legendarna")
                    .wymaganaSila(90)
                    .wymaganaZrecznosc(10)
                    .wymaganaInteligencja(10)
                    .wlasciciel(tmp2)
                    .build();

            Ekspedycja eks1 = Ekspedycja.builder()
                    .nazwa("Pierwsza ekspedycja")
                    .dataRozpoczecia(LocalDate.of(1337, 1, 13))
                    .dataZakonczenia(LocalDate.of(1337, 2, 13))
                    .status(StatusEkspedycji.Zakonczona)
                    .druzyna(Set.of(tmp1, mg2))
                    .ruina(sr1)
                    .build();

            Ekspedycja eks2 = Ekspedycja.builder()
                    .nazwa("Druga ekspedycja")
                    .dataRozpoczecia(LocalDate.of(1537, 1, 13))
                    .dataZakonczenia(LocalDate.of(1537, 1, 15))
                    .status(StatusEkspedycji.Odwolana)
                    .druzyna(Set.of(tmp1, mg2))
                    .ruina(sr2)
                    .build();

            Ekspedycja eks3 = Ekspedycja.builder()
                    .nazwa("Trzecia ekspedycja")
                    .dataRozpoczecia(LocalDate.now())
                    .status(StatusEkspedycji.Zaplanowana)
                    .ruina(sr1)
                    .druzyna(Set.of(tmp2, mg1))
                    .build();

            RozpoznanieWTerenie rwt1 = RozpoznanieWTerenie.builder()
                    .kraina(kr1)
                    .zwiadowca(zw1)
                    .procentRozpoznania(0.59)
                    .build();
            RozpoznanieWTerenie rwt2 = RozpoznanieWTerenie.builder()
                    .kraina(kr2)
                    .zwiadowca(zw2)
                    .procentRozpoznania(0.31)
                    .build();

            Praca praca1 = Praca.builder()
                    .dataRozpoczecia(LocalDate.now())
                    .ekspertyza(12)
                    .placa(2200)
                    .odkrywca(woj1)
                    .profesja(pr1)
                    .build();
            Praca praca2 = Praca.builder()
                    .dataRozpoczecia(LocalDate.now())
                    .ekspertyza(13)
                    .placa(2500)
                    .odkrywca(woj2)
                    .profesja(pr2)
                    .build();
            Praca praca3 = Praca.builder()
                    .dataRozpoczecia(LocalDate.now())
                    .ekspertyza(14)
                    .placa(3000)
                    .odkrywca(mg1)
                    .profesja(pr3)
                    .build();
            Praca praca4 = Praca.builder()
                    .dataRozpoczecia(LocalDate.now())
                    .ekspertyza(15)
                    .placa(1000)
                    .odkrywca(zw1)
                    .profesja(pr4)
                    .build();

            krainaRepository.saveAll(Arrays.asList(kr1, kr2, kr3));

            sr1.setKraina(kr1);
            sr2.setKraina(kr2);
            sr3.setKraina(kr3);
            sr4.setKraina(kr3);
            sr5.setKraina(kr3);
            sr6.setKraina(kr2);
            sr1.getStarozytni().add(str1);
            sr1.getStarozytni().add(str4);
            sr2.getStarozytni().add(str2);
            sr3.getStarozytni().add(str3);
            sr4.getStarozytni().add(str5);
            sr5.getStarozytni().add(str6);
            sr6.getStarozytni().add(str7);
            starozytnaRuinaRepository.saveAll(Arrays.asList(sr1, sr2, sr3, sr4, sr5, sr6));

            starozytnyRepository.saveAll(Arrays.asList(str1, str2, str3, str4, str5, str6, str7));
            przekletyStarozytnyRepository.saveAll(Arrays.asList(przekStr1, przekStr2));
            powietrznyStarozytnyRepository.saveAll(Arrays.asList(powStr1, powStr2));

            str2.setPrzekletyStarozytny(przekStr1);
            str3.setPowietrznyStarozytny(powStr1);
            str4.setPrzekletyStarozytny(przekStr2);
            str4.setPowietrznyStarozytny(powStr2);
            starozytnyRepository.saveAll(Arrays.asList(str1, str2, str3, str4, str5, str6, str7));

            magRepository.saveAll(Arrays.asList(mg1, mg2));
            wojownikRepository.saveAll(Arrays.asList(woj1, woj2));
            zwiadowcaRepository.saveAll(Arrays.asList(zw1, zw2));
            templariuszRepository.saveAll(Arrays.asList(tmp1, tmp2));

            br1.setWlasciciel(mg1);
            br2.setWlasciciel(mg2);
            br3.setWlasciciel(woj1);
            br4.setWlasciciel(woj2);
            br5.setWlasciciel(zw1);
            br6.setWlasciciel(zw2);
            br7.setWlasciciel(tmp1);
            br8.setWlasciciel(tmp2);
            bronRepository.saveAll(Arrays.asList(br1, br2, br3, br4, br5, br6, br7, br8));

            rozpoznanieWTerenieRepository.saveAll(Arrays.asList(rwt1, rwt2));
            profesjaRepository.saveAll(Arrays.asList(pr1, pr2, pr3, pr4));

            praca1.setOdkrywca(woj1);
            praca1.setProfesja(pr1);
            praca2.setOdkrywca(woj2);
            praca2.setProfesja(pr2);
            praca3.setOdkrywca(mg1);
            praca3.setProfesja(pr3);
            praca4.setOdkrywca(zw1);
            praca4.setProfesja(pr4);
            pracaRepository.saveAll(Arrays.asList(praca1, praca2, praca3, praca4));

            eks1.setDruzyna(Set.of(tmp1, mg2));
            eks1.setRuina(sr1);
            eks2.setDruzyna(Set.of(tmp2, mg1));
            eks2.setRuina(sr2);
            eks3.setDruzyna(Set.of(tmp2, mg1));
            eks3.setRuina(sr1);
            ekspedycjaRepository.saveAll(Arrays.asList(eks1, eks2, eks3));

            kr1.getWiedzaOTerenach().add(rwt1);
            zw1.getWiedzaOKrainach().add(rwt1);
            kr2.getWiedzaOTerenach().add(rwt2);
            zw2.getWiedzaOKrainach().add(rwt2);

            tmp2.getEkspedycje().add(eks3);
            mg1.getEkspedycje().add(eks3);
            tmp1.getEkspedycje().add(eks1);
            mg2.getEkspedycje().add(eks1);
            tmp1.getEkspedycje().add(eks2);
            mg2.getEkspedycje().add(eks2);

            sr1.getEkspedycje().add(eks1);
            sr2.getEkspedycje().add(eks2);
            sr1.getEkspedycje().add(eks3);

            woj1.getPrace().add(praca1);
            pr1.getPracujacy().add(praca1);
            woj2.getPrace().add(praca2);
            pr2.getPracujacy().add(praca2);
            mg1.getPrace().add(praca3);
            pr3.getPracujacy().add(praca3);
            zw1.getPrace().add(praca4);
            pr4.getPracujacy().add(praca4);

            mg1.getBronie().add(br1);
            mg2.getBronie().add(br2);
            woj1.getBronie().add(br3);
            woj2.getBronie().add(br4);
            zw1.getBronie().add(br5);
            zw2.getBronie().add(br6);
            tmp1.getBronie().add(br7);
            tmp2.getBronie().add(br8);
        }
    }
}
