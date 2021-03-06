package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        List<Persona> personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("personas", personas);
        var saldoTotal = 0D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}

/*
* ra, pactar??, pactarais, pact??ramos, pactaran, pactar??n, pactaras, pactar??s, pactare,
* pactar??, pactareis, pactar??is, pactaremos, pact??remos, pactaren, pactares, pactar??a, pactar??ais,\
*  pactar??amos, pactar??an, pactar??as, pactarla, pactarlas, pactarle, pactarles, pactarlo, pactarlos,
* pactaron, pactas, pactase, pactaseis, pact??semos, pactasen, pactases, pactaste, pactasteis, pacte, pact??,
* pact??is, pactemos, pacten, pactes, pactismo, pactista, pacto, pact??, pactos, pac??, padece, padeced, padec??is,
* padecemos, padecen, padecer, padecer??, padecer??n, padecer??s, padecer??, padecer??is, padeceremos, padecer??a,
*  padecer??ais, padecer??amos, padecer??an, padecer??as, padecerla, padecerlas, padecerle, padecerles, padecerlo, padecerlos, padecerme, padecernos,
* padeceros, padecerse, padecerte, padeces, padec??, padec??a, padec??ais, padec??amos, padec??an, padec??as, padecida, padecidas, padecido, padecidos, padeciendo,
*  padeci??ndola, padeci??ndolas, padeci??ndole, padeci??ndoles, padeci??ndolo, padeci??ndolos, padeci??ndome, padeci??ndonos,
*  padeci??ndoos, padeci??ndose, padeci??ndote, padeciera, padecierais, padeci??ramos, padecieran, padecieras, padeciere,
*  padeciereis, padeci??remos, padecieren, padecieres, padecieron, padeciese, padecieseis, padeci??semos, padeciesen, padecieses, padecimiento,
* padecimientos, padecimos, padeci??, padeciste, padecisteis, padezca, padezc??is, padezcamos, padezcan, padezcas, padezco, padilla, padillas, padrastro, padrastros, padrazo, padrazos, padre, padrear, padree,
*  padrej??n, padrejones, padrenuestro, padrenuestros, padres, padrillo, padrina, padrinas, padrinazgo, padrinazgos, padrino, padrinos, padr??n, padronero, padrones, padron??s, padronesa, padronesas, padroneses, padrote, padrotear, paduano,
* paella, paellas, paellera, paf, pafia, pafias, pafio, pafios, pafl??n, paflones, paga, pagaba, pagabais, pag??bamos, pagaban, pagabas, pagable, pagables, pagad, pagada, pagadas, pagadera, pagaderas, pagadero,
*  pagaderos, pagado, pagador, pagadora, pagadoras, pagadores, pagados, pagadur??a, pagadur??as, pag??is, pagamento, pagamiento, pagamos, pagan, pagana, paganas, pagando, pag??ndola, pag??ndolas, pag??ndole, pag??ndoles, pag??ndolo, pag??ndolos,
*  pag??ndome, pag??ndomela, pag??ndomelas, pag??ndomelo, pag??ndomelos, pag??ndonos, pag??ndonosla, pag??ndonoslas, pag??ndonoslo, pag??ndonoslos, pag??ndoos, pag??ndoosla, pag??ndooslas,
*  pag??ndooslo, pag??ndooslos, pag??ndose, pag??ndosela, pag??ndoselas, pag??ndoselo, pag??ndoselos, pag??ndote, pag??ndotela, pag??ndotelas, pag??ndotelo, pag??ndotelos, pagan??a, pagan??as, paganice, paganic??, paganic??is,
* paganicemos, paganicen, paganices, paganismo, paganismos, paganiza, paganizaba, paganizabais, paganiz??bamos, paganizaban, paganizabas, paganizad, paganizada, paganizadas, paganizado, paganizados, paganiz??is, paganizamos, paganizan,
* paganizando, paganizar, paganizara, paganizar??, paganizarais, paganiz??ramos, paganizaran, paganizar??n, paganizaras, paganizar??s, paganizare, paganizar??, paganizareis, paganizar??is,
* paganizaremos, paganiz??remos, paganizaren, paganizares, paganizar??a, paganizar??ais, paganizar??amos, paganizar??an,
* paganizar??as, paganizaron, paganizas, paganizase, paganizaseis, paganiz??semos, paganizasen, paganizases, paganizaste, paganizasteis, paganizo, paganiz??, pagano, paganos, pagar, pagara, pagar??, pagarais, pag??ramos, pagaran, pagar??n, pagaras, pagar??s, pagare, pagar??, pagareis, pagar??is, pagaremos, pag??remos, pagaren,
* pagares, pagar??s, pagar??a, pagar??ais, pagar??amos, pagar??an, pagar??as, pagarla, pagarlas, pagarle, pagarles, pagarlo, pagarlos, pagarme, pag??rmela, pag??rmelas, pag??rmelo, pag??rmelos,
*  pagarnos, pag??rnosla, pag??rnoslas, pag??rnoslo, pag??rnoslos, pagaron, pagaros,
*  pag??rosla, pag??roslas, pag??roslo, pag??roslos, pagarse, pag??rsela, pag??rselas, pag??rselo, pag??rselos, pagarte, pag??rtela, pag??rtelas, pag??rtelo, pag??rtelos, pagas, pagase, pagaseis, pag??semos, p
* agasen, pagases, pagaste, pagasteis, pagaya, pagayas, pagaza, page, pagel, pages,
* p??gina, paginaba, paginabais, pagin??bamos, paginaban, paginabas, paginaci??n, paginaciones, paginad, paginada, paginadas, paginado, paginador, paginadora, paginadoras, paginadores, paginados,
*  pagin??is, paginamos, paginan, paginando, paginar, paginara, paginar??, paginarais, pagin??ramos, paginaran, paginar??n, paginaras, paginar??s, paginare, paginar??, paginareis, paginar??is, paginaremos, pagin??remos,
* paginaren, paginares, paginar??a, paginar??ais, paginar??amos, paginar??an, paginar??as, paginaron, paginas, p??ginas, paginase, paginaseis, pagin??semos, paginasen, paginases,
* paginaste, paginasteis, pagine, pagin??, pagin??is, paginemos, paginen, pagines, pagino, pagin??, pago, pag??, pagoda, pagodas, pagos, pagote, pagro, p??gs, pagua, pa
* gue, pagu??, pagu??is, p??guelo, paguemos, paguen, p??guenlo, pagues, paguro, pahua, paico, paicos, paidolog??a, paidol??gico, paila, pailebot, pailebote, pailero
* , pail??n, pailones, paina, painel, paipai, paipay, pairar, paire, pairo, pairos, pa??s, paisaje, paisajes, paisajismo, paisajista, paisajistas, paisaj??stica,
* paisaj??sticas, paisaj??stico, paisaj??sticos, paisana, paisanaje, paisanajes, paisanas, paisano, paisanos, paises, pa??ses, paisista, paisistas, paja, pajado, pajar, p??jara, p??jaras, pajarea, pajareaba, pajareabais, pajare??bamos, pajareaban, pajareabas,
*  pajaread, pajareada, pajareadas, pajareado, pajareados, pajare??is, pajareamos, pajarean, pajareando, pajarear, pajareara, pajarear??, pajarearais, pajare??ramos, pajarearan, pajarear??n, pajarearas, pajarear??s, pajareare, pajarear??, pajareareis,
*  pajarear??is, pajarearemos, pajare??remos, pajarearen, pajareares, pajarear??a, pajarear??ais, pajarear??amos, pajarear??an, pajarear??as, pajarearon, pajareas, pajarease, pajareaseis, pajare??semos, pajareasen, pajareases, pajareaste, pajareasteis, pajaree, pajare??, pajare??is, pajareemos, pajareen, pajarees, pajarel, pajareo, pajare??, pajarera, pajareras, pajarer??a, pajarer??as, pajarero, pajareros, pajares, pajarete, pajarico, pajaricos, pajaril, pajarilla, pajarillas, pajarillo,
* \pajarillos, pajarita, pajaritas, pajarito, pajaritos, p??jaro, p??jaros, pajarota, pajarotada, pajarotadas, pajarotas, pajarote, pajarotes, pajarraco, pajarracos, pajaruco, pajarucos, pajas, pajaza, pajazas, pajazo, pajazos, paje, pajea, pajeaba, pajeabais, paje??bamos, pajeaban, pajeabas, pajead, pajeada, pajeadas, pajeado, pajeados, paje??is,
* pajeamos, pajean, pajeando, pajear, pajeara, pajear??, pajearais,
* paje??ramos, pajearan, pajear??n, pajearas, pajear??s, pajeare, pajear??, pajeareis, pajear??is, pajearemos, paje??remos, pajearen, pajeares, pajear??a, pajear??ais, pajear??amos, pajear??an, pajear??as, pajearon, pajeas, pajease, pajeaseis, paje??semos, pajeasen, pajeases, pajeaste, pajeasteis, pajecillo, pajecillos, pajee, paje??, paje??is, pajeemos, pajeen, pajees, pajel, pajeo, paje??, pajera, pajeras, pajer??a, pajer??as, pajero, pajeros, pajes, paji, pajil, pajilla, pajillas, pajiza,
*  pajizas, pajizo, pajizos, pajo, pajolera, pajoleras, pajolero, pajoleros, paj??n, pajonal, pajones, pajos, pajosa, pajosas, pajoso, pajosos, pajote, pajucero, pajuela, pajuerano, pajuil, pajuncio, pajuno, pajuz, pajuzo, pajuzos, pakistan, pakistan??, pakistan??s, pakistans, pal, pala, palabr, palabra, palabrada, palabras, palabrear, palabreja, palabrejas, palabreo, palabreos,
* \palabrera, palabreras, palabrer??a, palabrer??as, palabrer??o, palabrero, palabreros, palabrimujer, palabrimujeres, palabrista, palabristas, palabrita, palabritas, palabro, palabr??n, palabrona, palabronas, palabrones, palabrota, palabrotas, palacete, palacetes, palacial, palaciano,
* palaciega, palaciegas, palaciego, palaciegos, palacio, palacios, palada, paladar, paladares, paladas, paladea, paladeaba, paladeabais, palade??bamos, paladeaban, paladeabas, paladead, paladeada, paladeadas, paladeado, paladeados, palade??is, paladeamos, paladean, paladeando, palade??ndola, palade??ndolas, palade??ndole, palade??ndoles, palade??ndolo,
*  palade??ndolos, palade??ndome, palade??ndonos, palade??ndoos, palade??ndose, palade??ndote, paladear, paladeara, paladear??, paladearais, palade??ramos, paladearan, paladear??n, paladearas, paladear??s, paladeare, paladear??, paladeareis, paladear??is, paladearemos, palade??remos, paladearen, paladeares, paladear??a, paladear??ais, paladear??amos,
* paladear??an, paladear??as, paladearla, paladearlas, paladearle, paladearles, paladearlo, paladearlos, paladearme, paladearnos, paladearon, paladearos, paladearse, paladearte, paladeas, paladease, paladeaseis, palade??semos, paladeasen, paladeases, paladeaste, paladeasteis, paladee, palade??, palade??is, paladeemos, paladeen, paladees, paladeo, palade??, paladeos, paladial, palad??n, paladina, paladinamente,
* paladinas, paladines, paladino, paladinos, paladio, paladi??n, paladiones, paladios, palado, palados, palafito, palafitos, palafr??n, palafrenero, palahierro, palamallo, palamallos, palamenta, palamentas, palanca, palancada, palancadas, palancana, palancanas, palancas, palanciano, palanc??n, palangana, palanganas, palang??nas, palanganear, palanganero, palanganeros, palangre, palangrero, palangreros, palangres, palanquear, palanquee, palanquera, palanqueras, palanquero, palanqueros, palanqueta, palanquetas, palanquetazo, palanquilla, palanquillas,
* palanqu??n, palanquines, palas, palasan, palasanes, palastro, palastros, palatabilidad, palatal, palatales,
*  palatalice, palatalic??, palatalic??is, palatalicemos, palatalicen, palatalices, palataliza, palatalizaba, palatalizabais, palataliz??bamos, palatalizaban, palatalizabas, palatalizaci??n, palatalizad, palatalizada, palatalizadas, palatalizado, palatalizados, palataliz??is, palatalizamos, palatalizan, palatalizando, palatalizar, palatalizara, palatalizar??, palatalizarais, palataliz??ramos, palatalizaran, palatalizar??n, palatalizaras, palatalizar??s, palatalizare, palatalizar??,
* palatalizareis, palatalizar??is, palatalizaremos, palataliz??remos, palatalizaren, palatalizares, palatalizar??a, palatalizar??ais, palatalizar??amo
* s, palatalizar??an, palatalizar??as, palatalizaron, palatalizas, palatalizase, palatalizaseis, palataliz??semos, palatalizasen, palatalizases, palatalizaste, palatalizasteis, palatalizo, palataliz??, palatier, palatieres, palatina, palatinado, palatinados, palatinas, palatino, palatinos, palay, palazo, palaz??n, palazones, palazos, palca, palco, palcos, pal??, palea, paleaba, paleabais, pale??bamos, paleaban, paleabas, palead, paleada, paleadas, paleado, paleador,
*  paleadora, paleadoras, paleadores, paleados, pale??is, paleal, paleamos, palean, paleando, palear, paleara, palear??, palearais, pale??ramos, palearan, palear??n, palearas, palear??s, paleare, palear??, paleareis, palear??is, palearemos, pale??remos, palearen, paleares, palear??a, palear??ais, palear??amos, palear??an, palear??as, palearon, paleas, palease, paleaseis, pale??semos, paleasen, paleases, paleaste, paleasteis, palee, pale??, pale??is, paleemos, paleen, palees, palencia, palenque, palenquear, palense, palente,
* palentina, palentinas, palentino, palentinos, paleo, pale??, paleocenico, paleoceno, paleocristiano, paleofitopatolog??a, pale??geno, paleograf??a, paleograf??as, paleogr??fica, paleogr??ficas, paleogr??fico, paleogr??ficos, paleogr??fo, pale??grafo, pale??grafos, paleol??tica, paleol??ticas, paleol??tico, paleol??ticos, paleolito, pale??loga, pale??logas, paleolog??a, pale??logo, pale??logos, paleontograf??a, paleontograf??as, paleontogr??fica, paleontogr??ficas, paleontogr??fico, paleontogr??ficos, paleontolog??a, paleontolog??as, paleontol??gica, paleontol??gicas, paleontol??gico,
* paleontol??gicos, paleont??logo, paleont??logos, paleopatolog??a, paleopatol??gico, paleopat??logo, paleoterio, paleoterios, paleozoica, paleozoicas, paleozoico, paleozoicos, palera, paleras, paler??a, paler??as, palermitano, palero, paleros, pales, palestina, palestinas, palestino, palestinos, palestra, palestras, pal??strica, pal??stricas, pal??strico, pal??stricos, palestrita, paleta, paletada, paletadas, paletas, paletazo, paletazos, paletear, paletee, paleteo, paleteos, paleter??a, paletero, paleteros, paletilla, paletillas, paleto, palet??, palet??n, paletones, paletoque,
* paletos, palet??s, palhu??n, pali, palia, paliaba, paliabais, pali??bamos, paliaban, paliabas, paliacate, paliaci??n, paliad, paliada, paliadamente, paliadas, paliado, paliados, pali??is, paliamos, palian, paliando, paliar, paliara, paliar??, paliarais, pali??ramos, paliaran, paliar??n, paliaras, paliar??s, paliare, paliar??, paliareis, paliar??is, paliaremos, pali??remos, paliaren, paliares, paliar??a, paliar??ais, paliar??amos, paliar??an, paliar??as, paliaron, palias, paliase, paliaseis, pali??semos, paliasen, paliases, paliaste, paliasteis, paliativa, paliativas, paliativo, paliativos, paliatoria, paliatorias, paliatorio, paliatorios, p??lida, p??lidamente, p??lidas, palidece,
*  palideced, palidec??is, palidecemos, palidecen, palidecer, palidecer??, palidecer??n, palidecer??s, palidecer??, palidecer??is, palideceremos, palidecer??a, palidecer??ais, palidecer??amos, palidecer??an, palidecer??as, palideces, palidec??, palidec??a, palidec??ais, palidec??amos, palidec??an, palidec??as, palidecida, palidecidas, palidecido, palidecidos, palideciendo, palideciera, palidecierais, palideci??ramos, palidecieran, palidecieras, palideciere, palideciereis, palideci??remos, palidecieren, palidecieres, palidecieron, palideciese, palidecieseis, palideci??semos, palideciesen, palidecieses, palidecimos, palideci??, palideciste, palidecisteis,
* palidez, palidezca, palidezc??is, palidezcamos, palidezcan, palidezcas, palidezco, p??lido, p??lidos, paliducha, paliduchas, paliducho, paliduchos, palie, pali??, pal??e, pali??is, paliemos, palien, palier, palieres, palies, palillera, palilleras, palillero, palilleros, palillo, palillos, palimpsesto, pal??ndroma, pal??ndromas, pal??ndromo, pal??ndromos, palingenesia, palingenesias, palingen??sico, palinodia, palinodias, palinolog??a, palio, pali??, palios, palique, paliquear, paliquee, paliques, palisandro, palista, palitoque, palitoques, palitroque, palitroques,
* paliza, palizada, palizadas, palizas, paliz??n, palla, pallaco, pallacos, pallada, pallador, pallaquear, pallar, pallas, pallaza, palle, pallete, pall??n, pallones, palloza, palma, palm??ceo, palmacristi, palmad, palmada, palmadas, palmadilla, palmadillas, palmado, palmados, palman, palmar, palmares, palmar??s, palmaria, palmariamente, palmarias, palmario, palmarios, palmarlo, palmas, palmatoria, palmatorias, palme, palm??, palmea, palmeaba, palmeabais, palme??bamos, palmeaban, palmeabas, palmead, palmeada, palmeadas, palmeado, palmeados, palme??is, palmeamos, palmean, palmeando, palmear, palmeara, palmear??, palmearais, palme??ramos, palmearan, palmear??n, palmearas, palmear??s, palmeare,
*  palmear??, palmeareis, palmear??is, palmearemos, palme??remos, palmearen, palmeares, palmear??a, palmear??ais, palmear??amos, palmear??an, palmear??as, palmearon, palmeas, palmease, palmeaseis, palme??semos, palmeasen, palmeases, palmeaste, palmeasteis, palmee, palme??, palme??is, palmeemos, palmeen, palmees, palmejar, palmen, palmenta, palmentas, palmentero, palmenteros, palmeo, palme??, palmeos, palmera, palmeral, palmerales, palmeras, palmero, palmeros, palmesana, palmesanas, palmesano, palmesanos, palmeta, palmetas, palmetazo, palmetazos, palmiche, palmiches, palm??fera, palm??feras, palm??fero, palm??feros, palmilla, palmillas, palm??peda, palm??pedas, palm??pedo, palm??pedos, palmita, palmitas, palmitera, palmiteras, palmitiesa, palmitiesas, palmitieso, palmitiesos, palmito,
*  palmitos, palmo, palm??, palmos, palmotada, palmotea, palmoteaba, palmoteabais, palmote??bamos, palmoteaban, palmoteabas, palmotead, palmoteada, palmoteadas, palmoteado, palmoteador, palmoteadores, palmoteados, palmote??is, palmoteamos, palmotean, palmoteando, palmotear, palmoteara, palmotear??, palmotearais, palmote??ramos, palmotearan, palmotear??n, palmotearas, palmotear??s, palmoteare, palmotear??, palmoteareis, palmotear??is, palmotearemos, palmote??remos, palmotearen, palmoteares, palmotear??a, palmotear??ais, palmotear??amos, palmotear??an, palmotear??as, palmotearon, palmoteas, palmotease, palmoteaseis, palmote??semos, palmoteasen, palmoteases, palmoteaste, palmoteasteis, palmotee, palmote??,
*  palmote??is, palmoteemos, palmoteen, palmotees, palmoteo, palmote??, palmoteos, palo, paloduz, palomadura, palomaduras, palomar, palomares, palomariega, palomariegas, palomas, palomea, palomeaba, palomeabais, palome??bamos, palomeaban, palomeabas, palomead, palomeada, palomeadas, palomeado, palomeados, palome??is, palomeamos, palomean, palomeando, palomear, palomeara, palomear??, palomearais, palome??ramos, palomearan, palomear??n, palomearas, palomear??s, palomeare, palomear??, palomeareis, palomear??is, palomearemos, palome??remos, palomearen, palomeares, palomear??a, palomear??ais, palomear??amos, palomear??an, palomear??as, palomearon, palomeas, palomease, palomeaseis, palome??semos,
*  palomeasen, palomeases, palomeaste, palomeasteis, palomee, palome??, palome??is, palomeemos, palomeen, palomees, palomeo, palome??, palomera, palomeras, palomer??a, palomer??as, palomero, palomeros, palometa, palometas, palomilla, palomillas, palomina, palominas, palomino, palominos, palomita, palomitas, palomo, palomos, pal??n, palones, palor, palos, palotada, palotadas, palotazo, palotazos, palote, palotea, paloteaba, paloteabais, palote??bamos, paloteaban, paloteabas, palotead, paloteada,
* paloteadas, paloteado, paloteados, palote??is, paloteamos, palotean, paloteando, palotear, paloteara, palotear??, palotearais, palote??ramos, palotearan, palotear??n, palotearas, palotear??s, paloteare, palotear??, paloteareis, palotear??is, palotearemos, palote??remos, palotearen, paloteares, palotear??a, palotear??ais, palotear??amos, palotear??an, palotear??as, palotearon, paloteas, palotease, paloteaseis, palote??semos, paloteasen, paloteases, paloteaste, paloteasteis, palotee, palote??, palote??is, paloteemos, paloteen, palotees, paloteo, palote??, paloteos, palotes, palpa, palpaba, palpabais, palp??bamos, palpaban, palpabas, palpable, palpablemente, palpables, palpabls, palpaci??n, palpaciones, palpad, palpada, palpadas, palpado, palpados,
* palpadura, palpaduras, palp??is, palpall??n, palpamiento, palpamientos, palpamos, palpan, palpando, palp??ndola, palp??ndolas, palp??ndole, palp??ndoles, palp??ndolo, palp??ndolos, palp??ndomelo, palp??ndonoslo, palp??ndooslo, palp??ndoselo, palp??ndotelo, palpar, palpara, palpar??, palparais, palp??ramos, palparan, palpar??n, palparas, palpar??s, palpare, palpar??, palpareis, palpar??is, palparemos, palp??remos, palparen, palpares, palpar??a, palpar??ais, palpar??amos, palpar??an, palpar??as, palparla, palparlas, palparle, palparles, palparlo, palparlos, palparon, palpas, palpase, palpaseis, palp??semos, palpasen, palpases, palpaste, palpasteis, palpe, palp??, p??lpebra, palpebral, palp??is, palpemos, palpen, palpes, palpi, palpita, palpitaba,
* palpitabais, palpit??bamos, palpitaban, palpitabas, palpitaci??n, palpitaciones, palpitad, palpitada, palpitadas, palpitado, palpitados, palpit??is, palpitamos, palpitan, palpitando, palpitante, palpitantes, palpitar, palpitara, palpitar??, palpitarais, palpit??ramos, palpitaran, palpitar??n, palpitaras, palpitar??s, palpitare, palpitar??, palpitareis, palpitar??is, palpitaremos, palpit??remos, palpitaren, palpitares, palpitar??a, palpitar??ais, palpitar??amos, palpitar??an, palpitar??as, palpitaron, palpitas, palpitase, palpitaseis, palpit??semos, palpitasen, palpitases, palpitaste, palpitasteis, palpite, palpit??, palpit??is, palpitemos, palpiten, palpites, palpito, palpit??, p??lpito, palpo, palp??, palpos, palqui, palquista, palta, palto, palucha, paluchear, palucher??a, paludamento,
* paludamentos, palude, pal??dica, pal??dicas, pal??dico, palud??cola, pal??dicos, paludismo, paludismos, paludo,
* palumbario, palumbarios, palurda, palurdas, palurdo, palurdos, palustre, palustres, pamandabu??n.
*
*
* NO ME GUSTA QUE EN GITHUB APAREZCA HTML MAS UTILIZADO QUE JAVA
*
* */