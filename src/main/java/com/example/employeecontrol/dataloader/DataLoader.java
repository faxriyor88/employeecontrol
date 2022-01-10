package com.example.employeecontrol.dataloader;

import com.example.employeecontrol.model.*;
import com.example.employeecontrol.model.enums.Permission;
import com.example.employeecontrol.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            //Role qo'shish
            List<Permission> list1 = new ArrayList<>();
            list1.add(Permission.ADD);
            list1.add(Permission.EDIT);
            list1.add(Permission.VIEW);
            list1.add(Permission.DELETE);
            list1.add(Permission.DOWNLOAD);
            List<Permission> list2 = new ArrayList<>();
            list2.add(Permission.ADD_REGION);
            list2.add(Permission.EDIT_REGION);
            list2.add(Permission.DELETE_REGION);
            list2.add(Permission.VIEW_REGION);
            list2.add(Permission.DOWNLOAD_REGION);
            Role role1 = new Role("SUPERADMIN", list1);
            Role role2 = new Role("DIRECTOR", list1);
            Role role3 = new Role("REGION", list2);
            List<Role> roles = new ArrayList<>();
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
            roleRepository.saveAll(roles);
//
//            // Region qo'shish
            List<Region> regions = new ArrayList<>();
            Region toshkent = new Region("Тошкент");Region jizzax = new Region("Жиззах");
            Region samarqand = new Region("Самарқанд");Region namangan = new Region("Наманган");
            Region tarmoq = new Region("Tarmoq");Region xorazm = new Region("Хоразм");
            Region navoiy = new Region("Навоий");Region surxondaryo = new Region("Сурхондарё");
            Region qashqadaryo = new Region("Қашқадарё");Region buxoro = new Region("Бухоро");
            Region sirdaryo = new Region("Сирдарё");Region fargona = new Region("Фарғона");
            Region andijon = new Region("Андижон");Region qoraqalpoq = new Region("Қорақалпоғистон");
            regions.add(toshkent);regions.add(jizzax);regions.add(samarqand);regions.add(namangan);
            regions.add(andijon);regions.add(fargona);regions.add(sirdaryo);regions.add(surxondaryo);
            regions.add(qashqadaryo);regions.add(xorazm);regions.add(samarqand);regions.add(navoiy);
            regions.add(tarmoq);regions.add(buxoro);regions.add(qoraqalpoq);
            regionRepository.saveAll(regions);
//
//            //District qo'shish
            List<District> districts = new ArrayList<>();
            District xordis1 = new District("Хўжайли",qoraqalpoq);District xordis2 = new District("Тўрткўл", qoraqalpoq);
            District xordis3 = new District("Тахтакўпир", qoraqalpoq);District xordis4 = new District("Шуманай", qoraqalpoq);
            District xordis5 = new District("Қораўзак", qoraqalpoq);District xordis6 = new District("Қўнғирот", qoraqalpoq);
            District xordis7 = new District("Қанликўл", qoraqalpoq);District xordis8 = new District("Нукус", qoraqalpoq);
            District xordis9 = new District("Мўйноқ", qoraqalpoq);District xordis10 = new District("Кегейли", qoraqalpoq);
            District xordis11 = new District("Элликқалъа", qoraqalpoq);District xordis12 = new District("Чимбой", qoraqalpoq);
            District xordis13 = new District("Беруний", qoraqalpoq);District xordis14 = new District("Амударё", qoraqalpoq);
            districts.add(xordis1);districts.add(xordis2);districts.add(xordis3);
            districts.add(xordis4);districts.add(xordis5);districts.add(xordis6);
            districts.add(xordis7);districts.add(xordis8);districts.add(xordis9);
            districts.add(xordis10);districts.add(xordis11);districts.add(xordis12);
            districts.add(xordis13);districts.add(xordis14);
            District qordis1 = new District("Янгибозор",xorazm);
            District qordis2 = new District("Янгиариқ", xorazm);
            District qordis3 = new District("Урганч ",xorazm);
            District qordis4 = new District("Шовот", xorazm);
            District qordis5 = new District("Қўшкўпир ",xorazm);
            District qordis6 = new District("Хива", xorazm);
            District qordis7 = new District("Ҳазорасп ",xorazm);
            District qordis8 = new District("Хонқа", xorazm);
            District qordis9 = new District("Гурлан ",xorazm);
           District qordis10 = new District("Боғот", xorazm);
           District qordis11 = new District("Тупроққалъа ",xorazm);
            districts.add(qordis1);districts.add(qordis2);districts.add(qordis3);
            districts.add(qordis4);districts.add(qordis5);districts.add(qordis6);
            districts.add(qordis7);districts.add(qordis8);districts.add(qordis9);
            districts.add(qordis10);districts.add(qordis11);
            District navdis1 = new District("Учқудуқ",navoiy);District navdis2 = new District("Томди", navoiy);
            District navdis3 = new District("Нурота",navoiy);District navdis4 = new District("Навбаҳор", navoiy);
            District navdis5 = new District("Хатирчи",navoiy);District navdis6 = new District("Қизилтепа", navoiy);
            District navdis7 = new District("Кармана",navoiy);District navdis8 = new District("Конимех", navoiy);
            districts.add(navdis1);districts.add(navdis2);districts.add(navdis3);
            districts.add(navdis4);districts.add(navdis5);districts.add(navdis6);
            districts.add(navdis7);districts.add(navdis5);districts.add(navdis8);
            District buxdis1 = new District("Вобкент",buxoro);District buxdis2 = new District("Шофиркон", buxoro);
            District buxdis3 = new District("Ромитан",buxoro);District buxdis4 = new District("Пешку", buxoro);
            District buxdis5 = new District("Қоровулбозор",buxoro);District buxdis6 = new District("Қоракўл", buxoro);
            District buxdis7 = new District("Когон",buxoro);District buxdis8 = new District("Жондор", buxoro);
            District buxdis9 = new District("Ғиждувон",buxoro);District buxdis10 = new District("Олот", buxoro);
            District buxdis11 = new District("Бухоро", buxoro);
            districts.add(buxdis1);districts.add(buxdis2);districts.add(buxdis3);
            districts.add(buxdis4);districts.add(buxdis5);districts.add(buxdis6);
            districts.add(buxdis7);districts.add(buxdis8);districts.add(buxdis9);
            districts.add(buxdis10);districts.add(buxdis11);
            District samdis1 = new District("Ургут",samarqand);District samdis2 = new District("Тойлоқ", samarqand);
            District samdis3 = new District("Самарқанд",samarqand);District samdis4 = new District("Пастдарғом", samarqand);
            District samdis5 = new District("Паяриқ",samarqand);District samdis6 = new District("Пахтачи", samarqand);
            District samdis7 = new District("Оқдарё",samarqand);District samdis8 = new District("Нуробод", samarqand);
            District samdis9 = new District("Нарпай",samarqand);District samdis10 = new District("Қўшработ", samarqand);
            District samdis11 = new District("Каттақўрғон",samarqand);District samdis12 = new District("Жомбой", samarqand);
            District samdis13 = new District("Иштихон",samarqand);District samdis14 = new District("Булунғур", samarqand);
            districts.add(samdis1);districts.add(samdis2);districts.add(samdis3);
            districts.add(samdis4);districts.add(samdis5);districts.add(samdis6);
            districts.add(samdis7);districts.add(samdis8);districts.add(samdis9);
            districts.add(samdis10);districts.add(samdis11);districts.add(samdis12);
            districts.add(samdis13);districts.add(samdis14);
            District qashdis1 = new District("Яккабоғ",qashqadaryo);District qashdis2 = new District("Шаҳрисабз", qashqadaryo);
            District qashdis3 = new District("Нишон",qashqadaryo);District qashdis4 = new District("Муборак", qashqadaryo);
            District qashdis5 = new District("Миришкор",qashqadaryo);District qashdis6 = new District("Китоб", qashqadaryo);
            District qashdis7 = new District("Касби",qashqadaryo);District qashdis8 = new District("Косон", qashqadaryo);
            District qashdis9 = new District("Қарши",qashqadaryo);District qashdis10 = new District("Қамаши", qashqadaryo);
            District qashdis11 = new District("Ғузор",qashqadaryo);District qashdis12 = new District("Деҳқонобод", qashqadaryo);
            District qashdis13 = new District("Чироқчи",qashqadaryo);
            districts.add(qashdis1);districts.add(qashdis2);districts.add(qashdis3);
            districts.add(qashdis4);districts.add(qashdis5);districts.add(qashdis6);
            districts.add(qashdis7);districts.add(qashdis8);districts.add(qashdis9);
            districts.add(qashdis10);districts.add(qashdis11);districts.add(qashdis12);
            districts.add(qashdis13);
            District surdis1 = new District("Узун",surxondaryo);District surdis2 = new District("Термиз", surxondaryo);
            District surdis3 = new District("Шўрчи",surxondaryo);District surdis4 = new District("Шеробод", surxondaryo);
            District surdis5 = new District("Сариосиё",surxondaryo);District surdis6 = new District("Олтинсой", surxondaryo);
            District surdis7 = new District("Музработ",surxondaryo);District surdis8 = new District("Қумқўрғон", surxondaryo);
            District surdis9 = new District("Қизириқ",surxondaryo);District surdis10 = new District("Жарқўрғон", surxondaryo);
            District surdis11 = new District("Денов",surxondaryo);District surdis12 = new District("Бойсун ", surxondaryo);
            District surdis13 = new District("Ангор",surxondaryo);
            districts.add(surdis1);districts.add(surdis2);districts.add(surdis3);
            districts.add(surdis4);districts.add(surdis5);districts.add(surdis6);
            districts.add(surdis7);districts.add(surdis8);districts.add(surdis9);
            districts.add(surdis10);districts.add(surdis11);districts.add(surdis12);
            districts.add(surdis13);
            District jizdis1 = new District("Зарбдор",jizzax);District jizdis2 = new District("Зафаробод", jizzax);
            District jizdis3 = new District("Зомин",jizzax);District jizdis4 = new District("Янгиобод", jizzax);
            District jizdis5 = new District("Пахтакор",jizzax);District jizdis6 = new District("Шароф Рашидов", jizzax);
            District jizdis7 = new District("Мирзачўл",jizzax);District jizdis8 = new District("Ғаллаорол", jizzax);
            District jizdis9 = new District("Фориш",jizzax);District jizdis10 = new District("Дўстлик", jizzax);
            District jizdis11 = new District("Бахмал",jizzax);District jizdis12 = new District("Арнасой", jizzax);
            districts.add(jizdis1);districts.add(jizdis2);districts.add(jizdis3);
            districts.add(jizdis4);districts.add(jizdis5);districts.add(jizdis6);
            districts.add(jizdis7);districts.add(jizdis8);districts.add(jizdis9);
            districts.add(jizdis10);districts.add(jizdis11);districts.add(jizdis12);
            District sirdis1 = new District("Ширин",sirdaryo);District sirdis2 = new District("Янгиер", sirdaryo);
            District sirdis3 = new District("Сирдарё",sirdaryo);District sirdis4 = new District("Сардоба", sirdaryo);
            District sirdis5 = new District("Сайхунобод",sirdaryo);District sirdis6 = new District("Мирзаобод", sirdaryo);
            District sirdis7 = new District("Ховос",sirdaryo);District sirdis8 = new District("Гулистон", sirdaryo);
            District sirdis9 = new District("Боёвут",sirdaryo);District sirdis10 = new District("Оқолтин", sirdaryo);
            districts.add(sirdis1);districts.add(sirdis2);districts.add(sirdis3);
            districts.add(sirdis4);districts.add(sirdis5);districts.add(sirdis6);
            districts.add(sirdis7);districts.add(sirdis8);districts.add(sirdis9);
            districts.add(sirdis10);
            District toshvdis1 = new District("Зангиота",toshkent);District toshvdis2 = new District("Юқори Чирчиқ", toshkent);
            District toshvdis3 = new District("Янгийўл",toshkent);District toshvdis4 = new District("Ўрта Чирчиқ", toshkent);
            District toshvdis5 = new District("Қуйи чирчиқ",toshkent);District toshvdis6 = new District("Пискент", toshkent);
            District toshvdis7 = new District("Паркент",toshkent);District toshvdis8 = new District("Оққўрғон", toshkent);
            District toshvdis9 = new District("Оҳангарон",toshkent);District toshvdis10 = new District("Қибрай", toshkent);
            District toshvdis11 = new District("Чиноз",toshkent);District toshvdis12 = new District("Бўка", toshkent);
            District toshvdis13 = new District("Бўстонлиқ",toshkent);District toshvdis14 = new District("Бекобод", toshkent);
            districts.add(toshvdis1);districts.add(toshvdis2);districts.add(toshvdis3);
            districts.add(toshvdis4);districts.add(toshvdis5);districts.add(toshvdis6);
            districts.add(toshvdis7);districts.add(toshvdis8);districts.add(toshvdis9);
            districts.add(toshvdis10);districts.add(toshvdis11);districts.add(toshvdis12);
            districts.add(toshvdis13);districts.add(toshvdis14);
            District namdis1 = new District("Янгиқўрғон",namangan);District namdis2 = new District("Уйчи", namangan);
            District namdis3 = new District("Учқўрғон",namangan);District namdis4 = new District("Тўрақўрғон", namangan);
            District namdis5 = new District("Поп",namangan);District namdis6 = new District("Норин(Ўзбекистон)", namangan);
            District namdis7 = new District("Наманган",namangan);District namdis8 = new District("Мингбулоқ", namangan);
            District namdis9 = new District("Косонсой",namangan);District namdis10 = new District("Чуст", namangan);
            District namdis11 = new District("Чортоқ",namangan);
            districts.add(namdis1);districts.add(namdis2);districts.add(namdis3);
            districts.add(namdis4);districts.add(namdis5);districts.add(namdis6);
            districts.add(namdis7);districts.add(namdis8);districts.add(namdis9);
            districts.add(namdis10);districts.add(namdis10);districts.add(namdis11);
            District fardis1 = new District("Қувасой",fargona);District fardis2 = new District("Ёзёвон", fargona);
            District fardis3 = new District("Ўзбекистон",fargona);District fardis4 = new District("Учкўприк", fargona);
            District fardis5 = new District("Тошлоқ",fargona);District fardis6 = new District("Сўх", fargona);
            District fardis7 = new District("Риштон",fargona);District fardis8 = new District("Қува", fargona);
            District fardis9 = new District("Қўштепа",fargona);District fardis10 = new District("Фурқат", fargona);
            District fardis11 = new District("Фарғона",fargona);District fardis12 = new District("Данғара", fargona);
            District fardis13 = new District("Бувайда",fargona);District fardis14 = new District("Бешариқ",fargona);
            District fardis15 = new District("Бағдод",fargona);District fardis16 = new District("Олтиариқ",fargona);
            districts.add(fardis1);districts.add(fardis2);districts.add(fardis3);
            districts.add(fardis4);districts.add(fardis5);districts.add(fardis6);
            districts.add(fardis7);districts.add(fardis8);districts.add(fardis9);
            districts.add(fardis10);districts.add(fardis11);districts.add(fardis12);
            districts.add(fardis13);districts.add(fardis14);districts.add(fardis15);
            districts.add(fardis16);
            District anddis1 = new District("Улуғнор",andijon);District anddis2 = new District("Шаҳрихон", andijon);
            District anddis3 = new District("Пахтаобод",andijon);District anddis4 = new District("Олтинкўл", andijon);
            District anddis5 = new District("Марҳамат",andijon);District anddis6 = new District("Қўрғонтепа", andijon);
            District anddis7 = new District("Хўжаобод",andijon);District anddis8 = new District("Жалақудуқ", andijon);
            District anddis9 = new District("Избоскан",andijon);District anddis10 = new District("Булоқбоши", andijon);
            District anddis11 = new District("Бўз",andijon);District anddis12 = new District("Балиқчи", andijon);
            District anddis13 = new District("Асака",andijon);District anddis14 = new District("Андижон", andijon);
            districts.add(anddis1);districts.add(anddis2);districts.add(anddis3);
            districts.add(anddis4);districts.add(anddis5);districts.add(anddis6);
            districts.add(anddis7);districts.add(anddis8);districts.add(anddis9);
            districts.add(anddis10);districts.add(anddis11);districts.add(anddis12);
            districts.add(anddis13);districts.add(anddis14);
            District toshsdis1 = new District("Юнусобод",toshkent);District toshsdis2 = new District("Яккасарой", toshkent);
            District toshsdis3 = new District("Учтепа(Тошкент)",toshkent);District toshsdis4 = new District("Олмазор", toshkent);
            District toshsdis5 = new District("Шайхонтоҳур",toshkent);District toshsdis6 = new District("Сергели", toshkent);
            District toshsdis7 = new District("Мирзо Улуғбек",toshkent);District toshsdis8 = new District("Миробод", toshkent);
            District toshsdis9 = new District("Ҳамза",toshkent);District toshsdis10 = new District("Чилонзор", toshkent);
            District toshsdis11 = new District("Бектемир",toshkent);
            districts.add(toshsdis1);districts.add(toshsdis2);districts.add(toshsdis3);
            districts.add(toshsdis4);districts.add(toshsdis5);districts.add(toshsdis6);
            districts.add(toshsdis7);districts.add(toshsdis8);districts.add(toshsdis9);
            districts.add(toshsdis10);districts.add(toshsdis11);

           districtRepository.saveAll(districts);
//            //Company qo'shish
            List<Company> companies = new ArrayList<>();
            Company toshscom = new Company( "Ўзбекистон Республикаси касаба уюшмалари Федерацияси Тошкент шаҳар кенгаши", toshkent);
            Company toshcom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Тошкент вилоят кенгаши", toshkent);
            Company andijoncom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Андижон вилоят кенгаши", andijon);
            Company qoraqalpoqcom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Қорақалпоғистон вилоят кенгаши", qoraqalpoq);
            Company fargonacom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Фарғона вилоят кенгаши ", fargona);
            Company namangancom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Наманган вилоят кенгаши	 ", namangan);
            Company sirdaryocom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Сирдарё вилоят кенгаши ", sirdaryo);
            Company jizzaxcom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Жиззах вилоят кенгаши ", jizzax);
            Company samarqandcom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Самарқанд вилоят кенгаши ", samarqand);
            Company buxorocom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Бухоро  вилоят кенгаши ", buxoro);
            Company qashqadaryocom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Қашқадарё вилоят кенгаши ", qashqadaryo);
            Company surxondaryocomp = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Сурхондарё вилоят кенгаши ", surxondaryo);
            Company navoiycom= new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Навоий вилоят кенгаши ", navoiy);
            Company xorazmcom = new Company("Ўзбекистон Республикаси касаба уюшмалари Федерацияси Хоразм вилоят кенгаши ", xorazm);

            Company tarkon=new Company("Навоий кон-металлургия комбинати ходимлари касаба уюшмаси Кенгаши",tarmoq);
            Company tarolma=new Company("«Олмалиқ КМК» АЖ ходимлари касаба уюшмаси Кенгаши",tarmoq);
            Company tarenergetika=new Company("Ўзбекистон энергетика, нефт-газ ва геология ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tartrans=new Company("Ўзбекистон транспорт, йўл ва капитал қурилиш, қурилиш индустрияси ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tartemir=new Company("Ўзбекистон темирйўлчилари ва транспорт қурувчилари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tartalim=new Company("Ўзбекистон таълим ва фан ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tartadbir=new Company("Ўзбекистон тадбиркорлик, бизнес ва хизмат кўрсатиш соҳалари ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tarsog=new Company("Ўзбекистон соғлиқни сақлаш ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tarmetal=new Company("Ўзбекистон металлургия ва машинасозлик саноати тармоқлари ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tarmadaniyat=new Company("Ўзбекистон маданият, спорт ва туризм ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tarkimiyo=new Company("Ўзбекистон кимё ва фармацевтика саноати ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company tardavlat=new Company("Ўзбекистон давлат муассасалари ва жамоат хизмати ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company taraxborot=new Company("Ўзбекистон ахборот технологиялари ва оммавий коммуникация ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company taragrosanoat=new Company("Ўзбекистон агросаноат мажмуи ходимлари касаба уюшмаси Республика Кенгаши",tarmoq);
            Company taravia=new Company("Ўзбекистон авиаходимлар касаба уюшмаси Республика Кенгаши",tarmoq);
            companies.add(toshscom);companies.add(toshcom);companies.add(andijoncom);companies.add(qoraqalpoqcom);
            companies.add(fargonacom);companies.add(namangancom);companies.add(sirdaryocom);
            companies.add(jizzaxcom);companies.add(samarqandcom);companies.add(buxorocom);
            companies.add(qashqadaryocom);companies.add(navoiycom);companies.add(surxondaryocomp);
            companies.add(xorazmcom);
            companies.add(tarkon);companies.add(tarolma);companies.add(tarenergetika);
            companies.add(tartrans);companies.add(tartemir);companies.add(tartalim);
            companies.add(tartadbir);companies.add(tarsog);companies.add(tarmetal);
            companies.add(tarmadaniyat);companies.add(tarkimiyo);companies.add(tardavlat);
            companies.add(taraxborot);companies.add(taragrosanoat);companies.add(taravia);
         companyRepository.saveAll(companies);
//            //Boshqaruvchi qo'shish
            List<Manager> managers = new ArrayList<>();
            Manager manager = new Manager("Super", "Superov", "tt", passwordEncoder.encode("tt"), role1, null, true);
            Manager manager1 = new Manager("Director", "Directorov", "ttd", passwordEncoder.encode("ttd"), role2, toshscom, true);
            Manager manager2 = new Manager("Region1", "Regionov1", "ttr1", passwordEncoder.encode("ttr1"), role3, jizzaxcom, true);
            Manager manager3 = new Manager("Region2", "Regionov2", "ttr2", passwordEncoder.encode("ttr2"), role3, andijoncom, true);
            Manager manager4 = new Manager("Region2", "Regionov2", "ttr3", passwordEncoder.encode("ttr3"), role3, qoraqalpoqcom, true);
            Manager manager5 = new Manager("Region2", "Regionov2", "ttr4", passwordEncoder.encode("ttr4"), role3, surxondaryocomp, true);
            Manager manager6 = new Manager("Region2", "Regionov2", "ttr5", passwordEncoder.encode("ttr5"), role3, taragrosanoat, true);
            Manager manager7 = new Manager("Region2", "Regionov2", "ttr6", passwordEncoder.encode("ttr6"), role3, taraxborot, true);
            Manager manager8 = new Manager("Region2", "Regionov2", "ttr7", passwordEncoder.encode("ttr7"), role3, tarkimiyo, true);
            managers.add(manager);managers.add(manager1);managers.add(manager2);managers.add(manager3);
            managers.add(manager4);managers.add(manager5);managers.add(manager6);managers.add(manager7);
            managers.add(manager8);
            managerRepository.saveAll(managers);
//
       }

    }

}