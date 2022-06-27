package mx.com.gm.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}


/*
 * ra, pactará, pactarais, pactáramos, pactaran, pactarán, pactaras, pactarás, pactare,
 * pactaré, pactareis, pactaréis, pactaremos, pactáremos, pactaren, pactares, pactaría, pactaríais,\
 *  pactaríamos, pactarían, pactarías, pactarla, pactarlas, pactarle, pactarles, pactarlo, pactarlos,
 * pactaron, pactas, pactase, pactaseis, pactásemos, pactasen, pactases, pactaste, pactasteis, pacte, pacté,
 * pactéis, pactemos, pacten, pactes, pactismo, pactista, pacto, pactó, pactos, pacú, padece, padeced, padecéis,
 * padecemos, padecen, padecer, padecerá, padecerán, padecerás, padeceré, padeceréis, padeceremos, padecería,
 *  padeceríais, padeceríamos, padecerían, padecerías, padecerla, padecerlas, padecerle, padecerles, padecerlo, padecerlos, padecerme, padecernos,
 * padeceros, padecerse, padecerte, padeces, padecí, padecía, padecíais, padecíamos, padecían, padecías, padecida, padecidas, padecido, padecidos, padeciendo,
 *  padeciéndola, padeciéndolas, padeciéndole, padeciéndoles, padeciéndolo, padeciéndolos, padeciéndome, padeciéndonos,
 *  padeciéndoos, padeciéndose, padeciéndote, padeciera, padecierais, padeciéramos, padecieran, padecieras, padeciere,
 *  padeciereis, padeciéremos, padecieren, padecieres, padecieron, padeciese, padecieseis, padeciésemos, padeciesen, padecieses, padecimiento,
 * padecimientos, padecimos, padeció, padeciste, padecisteis, padezca, padezcáis, padezcamos, padezcan, padezcas, padezco, padilla, padillas, padrastro, padrastros, padrazo, padrazos, padre, padrear, padree,
 *  padrejón, padrejones, padrenuestro, padrenuestros, padres, padrillo, padrina, padrinas, padrinazgo, padrinazgos, padrino, padrinos, padrón, padronero, padrones, padronés, padronesa, padronesas, padroneses, padrote, padrotear, paduano,
 * paella, paellas, paellera, paf, pafia, pafias, pafio, pafios, paflón, paflones, paga, pagaba, pagabais, pagábamos, pagaban, pagabas, pagable, pagables, pagad, pagada, pagadas, pagadera, pagaderas, pagadero,
 *  pagaderos, pagado, pagador, pagadora, pagadoras, pagadores, pagados, pagaduría, pagadurías, pagáis, pagamento, pagamiento, pagamos, pagan, pagana, paganas, pagando, pagándola, pagándolas, pagándole, pagándoles, pagándolo, pagándolos,
 *  pagándome, pagándomela, pagándomelas, pagándomelo, pagándomelos, pagándonos, pagándonosla, pagándonoslas, pagándonoslo, pagándonoslos, pagándoos, pagándoosla, pagándooslas,
 *  pagándooslo, pagándooslos, pagándose, pagándosela, pagándoselas, pagándoselo, pagándoselos, pagándote, pagándotela, pagándotelas, pagándotelo, pagándotelos, paganía, paganías, paganice, paganicé, paganicéis,
 * paganicemos, paganicen, paganices, paganismo, paganismos, paganiza, paganizaba, paganizabais, paganizábamos, paganizaban, paganizabas, paganizad, paganizada, paganizadas, paganizado, paganizados, paganizáis, paganizamos, paganizan,
 * paganizando, paganizar, paganizara, paganizará, paganizarais, paganizáramos, paganizaran, paganizarán, paganizaras, paganizarás, paganizare, paganizaré, paganizareis, paganizaréis,
 * paganizaremos, paganizáremos, paganizaren, paganizares, paganizaría, paganizaríais, paganizaríamos, paganizarían,
 * paganizarías, paganizaron, paganizas, paganizase, paganizaseis, paganizásemos, paganizasen, paganizases, paganizaste, paganizasteis, paganizo, paganizó, pagano, paganos, pagar, pagara, pagará, pagarais, pagáramos, pagaran, pagarán, pagaras, pagarás, pagare, pagaré, pagareis, pagaréis, pagaremos, pagáremos, pagaren,
 * pagares, pagarés, pagaría, pagaríais, pagaríamos, pagarían, pagarías, pagarla, pagarlas, pagarle, pagarles, pagarlo, pagarlos, pagarme, pagármela, pagármelas, pagármelo, pagármelos,
 *  pagarnos, pagárnosla, pagárnoslas, pagárnoslo, pagárnoslos, pagaron, pagaros,
 *  pagárosla, pagároslas, pagároslo, pagároslos, pagarse, pagársela, pagárselas, pagárselo, pagárselos, pagarte, pagártela, pagártelas, pagártelo, pagártelos, pagas, pagase, pagaseis, pagásemos, p
 * agasen, pagases, pagaste, pagasteis, pagaya, pagayas, pagaza, page, pagel, pages,
 * página, paginaba, paginabais, paginábamos, paginaban, paginabas, paginación, paginaciones, paginad, paginada, paginadas, paginado, paginador, paginadora, paginadoras, paginadores, paginados,
 *  pagináis, paginamos, paginan, paginando, paginar, paginara, paginará, paginarais, pagináramos, paginaran, paginarán, paginaras, paginarás, paginare, paginaré, paginareis, paginaréis, paginaremos, pagináremos,
 * paginaren, paginares, paginaría, paginaríais, paginaríamos, paginarían, paginarías, paginaron, paginas, páginas, paginase, paginaseis, paginásemos, paginasen, paginases,
 * paginaste, paginasteis, pagine, paginé, paginéis, paginemos, paginen, pagines, pagino, paginó, pago, pagó, pagoda, pagodas, pagos, pagote, pagro, págs, pagua, pa
 * gue, pagué, paguéis, páguelo, paguemos, paguen, páguenlo, pagues, paguro, pahua, paico, paicos, paidología, paidológico, paila, pailebot, pailebote, pailero
 * , pailón, pailones, paina, painel, paipai, paipay, pairar, paire, pairo, pairos, país, paisaje, paisajes, paisajismo, paisajista, paisajistas, paisajística,
 * paisajísticas, paisajístico, paisajísticos, paisana, paisanaje, paisanajes, paisanas, paisano, paisanos, paises, países, paisista, paisistas, paja, pajado, pajar, pájara, pájaras, pajarea, pajareaba, pajareabais, pajareábamos, pajareaban, pajareabas,
 *  pajaread, pajareada, pajareadas, pajareado, pajareados, pajareáis, pajareamos, pajarean, pajareando, pajarear, pajareara, pajareará, pajarearais, pajareáramos, pajarearan, pajarearán, pajarearas, pajarearás, pajareare, pajarearé, pajareareis,
 *  pajarearéis, pajarearemos, pajareáremos, pajarearen, pajareares, pajarearía, pajarearíais, pajarearíamos, pajarearían, pajarearías, pajarearon, pajareas, pajarease, pajareaseis, pajareásemos, pajareasen, pajareases, pajareaste, pajareasteis, pajaree, pajareé, pajareéis, pajareemos, pajareen, pajarees, pajarel, pajareo, pajareó, pajarera, pajareras, pajarería, pajarerías, pajarero, pajareros, pajares, pajarete, pajarico, pajaricos, pajaril, pajarilla, pajarillas, pajarillo,
 * \pajarillos, pajarita, pajaritas, pajarito, pajaritos, pájaro, pájaros, pajarota, pajarotada, pajarotadas, pajarotas, pajarote, pajarotes, pajarraco, pajarracos, pajaruco, pajarucos, pajas, pajaza, pajazas, pajazo, pajazos, paje, pajea, pajeaba, pajeabais, pajeábamos, pajeaban, pajeabas, pajead, pajeada, pajeadas, pajeado, pajeados, pajeáis,
 * pajeamos, pajean, pajeando, pajear, pajeara, pajeará, pajearais,
 * pajeáramos, pajearan, pajearán, pajearas, pajearás, pajeare, pajearé, pajeareis, pajearéis, pajearemos, pajeáremos, pajearen, pajeares, pajearía, pajearíais, pajearíamos, pajearían, pajearías, pajearon, pajeas, pajease, pajeaseis, pajeásemos, pajeasen, pajeases, pajeaste, pajeasteis, pajecillo, pajecillos, pajee, pajeé, pajeéis, pajeemos, pajeen, pajees, pajel, pajeo, pajeó, pajera, pajeras, pajería, pajerías, pajero, pajeros, pajes, paji, pajil, pajilla, pajillas, pajiza,
 *  pajizas, pajizo, pajizos, pajo, pajolera, pajoleras, pajolero, pajoleros, pajón, pajonal, pajones, pajos, pajosa, pajosas, pajoso, pajosos, pajote, pajucero, pajuela, pajuerano, pajuil, pajuncio, pajuno, pajuz, pajuzo, pajuzos, pakistan, pakistaní, pakistanís, pakistans, pal, pala, palabr, palabra, palabrada, palabras, palabrear, palabreja, palabrejas, palabreo, palabreos,
 * \palabrera, palabreras, palabrería, palabrerías, palabrerío, palabrero, palabreros, palabrimujer, palabrimujeres, palabrista, palabristas, palabrita, palabritas, palabro, palabrón, palabrona, palabronas, palabrones, palabrota, palabrotas, palacete, palacetes, palacial, palaciano,
 * palaciega, palaciegas, palaciego, palaciegos, palacio, palacios, palada, paladar, paladares, paladas, paladea, paladeaba, paladeabais, paladeábamos, paladeaban, paladeabas, paladead, paladeada, paladeadas, paladeado, paladeados, paladeáis, paladeamos, paladean, paladeando, paladeándola, paladeándolas, paladeándole, paladeándoles, paladeándolo,
 *  paladeándolos, paladeándome, paladeándonos, paladeándoos, paladeándose, paladeándote, paladear, paladeara, paladeará, paladearais, paladeáramos, paladearan, paladearán, paladearas, paladearás, paladeare, paladearé, paladeareis, paladearéis, paladearemos, paladeáremos, paladearen, paladeares, paladearía, paladearíais, paladearíamos,
 * paladearían, paladearías, paladearla, paladearlas, paladearle, paladearles, paladearlo, paladearlos, paladearme, paladearnos, paladearon, paladearos, paladearse, paladearte, paladeas, paladease, paladeaseis, paladeásemos, paladeasen, paladeases, paladeaste, paladeasteis, paladee, paladeé, paladeéis, paladeemos, paladeen, paladees, paladeo, paladeó, paladeos, paladial, paladín, paladina, paladinamente,
 * paladinas, paladines, paladino, paladinos, paladio, paladión, paladiones, paladios, palado, palados, palafito, palafitos, palafrén, palafrenero, palahierro, palamallo, palamallos, palamenta, palamentas, palanca, palancada, palancadas, palancana, palancanas, palancas, palanciano, palancón, palangana, palanganas, palangánas, palanganear, palanganero, palanganeros, palangre, palangrero, palangreros, palangres, palanquear, palanquee, palanquera, palanqueras, palanquero, palanqueros, palanqueta, palanquetas, palanquetazo, palanquilla, palanquillas,
 * palanquín, palanquines, palas, palasan, palasanes, palastro, palastros, palatabilidad, palatal, palatales,
 *  palatalice, palatalicé, palatalicéis, palatalicemos, palatalicen, palatalices, palataliza, palatalizaba, palatalizabais, palatalizábamos, palatalizaban, palatalizabas, palatalización, palatalizad, palatalizada, palatalizadas, palatalizado, palatalizados, palatalizáis, palatalizamos, palatalizan, palatalizando, palatalizar, palatalizara, palatalizará, palatalizarais, palatalizáramos, palatalizaran, palatalizarán, palatalizaras, palatalizarás, palatalizare, palatalizaré,
 * palatalizareis, palatalizaréis, palatalizaremos, palatalizáremos, palatalizaren, palatalizares, palatalizaría, palatalizaríais, palatalizaríamo
 * s, palatalizarían, palatalizarías, palatalizaron, palatalizas, palatalizase, palatalizaseis, palatalizásemos, palatalizasen, palatalizases, palatalizaste, palatalizasteis, palatalizo, palatalizó, palatier, palatieres, palatina, palatinado, palatinados, palatinas, palatino, palatinos, palay, palazo, palazón, palazones, palazos, palca, palco, palcos, palé, palea, paleaba, paleabais, paleábamos, paleaban, paleabas, palead, paleada, paleadas, paleado, paleador,
 *  paleadora, paleadoras, paleadores, paleados, paleáis, paleal, paleamos, palean, paleando, palear, paleara, paleará, palearais, paleáramos, palearan, palearán, palearas, palearás, paleare, palearé, paleareis, palearéis, palearemos, paleáremos, palearen, paleares, palearía, palearíais, palearíamos, palearían, palearías, palearon, paleas, palease, paleaseis, paleásemos, paleasen, paleases, paleaste, paleasteis, palee, paleé, paleéis, paleemos, paleen, palees, palencia, palenque, palenquear, palense, palente,
 * palentina, palentinas, palentino, palentinos, paleo, paleó, paleocenico, paleoceno, paleocristiano, paleofitopatología, paleógeno, paleografía, paleografías, paleográfica, paleográficas, paleográfico, paleográficos, paleográfo, paleógrafo, paleógrafos, paleolítica, paleolíticas, paleolítico, paleolíticos, paleolito, paleóloga, paleólogas, paleología, paleólogo, paleólogos, paleontografía, paleontografías, paleontográfica, paleontográficas, paleontográfico, paleontográficos, paleontología, paleontologías, paleontológica, paleontológicas, paleontológico,
 * paleontológicos, paleontólogo, paleontólogos, paleopatología, paleopatológico, paleopatólogo, paleoterio, paleoterios, paleozoica, paleozoicas, paleozoico, paleozoicos, palera, paleras, palería, palerías, palermitano, palero, paleros, pales, palestina, palestinas, palestino, palestinos, palestra, palestras, paléstrica, paléstricas, paléstrico, paléstricos, palestrita, paleta, paletada, paletadas, paletas, paletazo, paletazos, paletear, paletee, paleteo, paleteos, paletería, paletero, paleteros, paletilla, paletillas, paleto, paletó, paletón, paletones, paletoque,
 * paletos, paletós, palhuén, pali, palia, paliaba, paliabais, paliábamos, paliaban, paliabas, paliacate, paliación, paliad, paliada, paliadamente, paliadas, paliado, paliados, paliáis, paliamos, palian, paliando, paliar, paliara, paliará, paliarais, paliáramos, paliaran, paliarán, paliaras, paliarás, paliare, paliaré, paliareis, paliaréis, paliaremos, paliáremos, paliaren, paliares, paliaría, paliaríais, paliaríamos, paliarían, paliarías, paliaron, palias, paliase, paliaseis, paliásemos, paliasen, paliases, paliaste, paliasteis, paliativa, paliativas, paliativo, paliativos, paliatoria, paliatorias, paliatorio, paliatorios, pálida, pálidamente, pálidas, palidece,
 *  palideced, palidecéis, palidecemos, palidecen, palidecer, palidecerá, palidecerán, palidecerás, palideceré, palideceréis, palideceremos, palidecería, palideceríais, palideceríamos, palidecerían, palidecerías, palideces, palidecí, palidecía, palidecíais, palidecíamos, palidecían, palidecías, palidecida, palidecidas, palidecido, palidecidos, palideciendo, palideciera, palidecierais, palideciéramos, palidecieran, palidecieras, palideciere, palideciereis, palideciéremos, palidecieren, palidecieres, palidecieron, palideciese, palidecieseis, palideciésemos, palideciesen, palidecieses, palidecimos, palideció, palideciste, palidecisteis,
 * palidez, palidezca, palidezcáis, palidezcamos, palidezcan, palidezcas, palidezco, pálido, pálidos, paliducha, paliduchas, paliducho, paliduchos, palie, palié, palíe, paliéis, paliemos, palien, palier, palieres, palies, palillera, palilleras, palillero, palilleros, palillo, palillos, palimpsesto, palíndroma, palíndromas, palíndromo, palíndromos, palingenesia, palingenesias, palingenésico, palinodia, palinodias, palinología, palio, palió, palios, palique, paliquear, paliquee, paliques, palisandro, palista, palitoque, palitoques, palitroque, palitroques,
 * paliza, palizada, palizadas, palizas, palizón, palla, pallaco, pallacos, pallada, pallador, pallaquear, pallar, pallas, pallaza, palle, pallete, pallón, pallones, palloza, palma, palmáceo, palmacristi, palmad, palmada, palmadas, palmadilla, palmadillas, palmado, palmados, palman, palmar, palmares, palmarés, palmaria, palmariamente, palmarias, palmario, palmarios, palmarlo, palmas, palmatoria, palmatorias, palme, palmé, palmea, palmeaba, palmeabais, palmeábamos, palmeaban, palmeabas, palmead, palmeada, palmeadas, palmeado, palmeados, palmeáis, palmeamos, palmean, palmeando, palmear, palmeara, palmeará, palmearais, palmeáramos, palmearan, palmearán, palmearas, palmearás, palmeare,
 *  palmearé, palmeareis, palmearéis, palmearemos, palmeáremos, palmearen, palmeares, palmearía, palmearíais, palmearíamos, palmearían, palmearías, palmearon, palmeas, palmease, palmeaseis, palmeásemos, palmeasen, palmeases, palmeaste, palmeasteis, palmee, palmeé, palmeéis, palmeemos, palmeen, palmees, palmejar, palmen, palmenta, palmentas, palmentero, palmenteros, palmeo, palmeó, palmeos, palmera, palmeral, palmerales, palmeras, palmero, palmeros, palmesana, palmesanas, palmesano, palmesanos, palmeta, palmetas, palmetazo, palmetazos, palmiche, palmiches, palmífera, palmíferas, palmífero, palmíferos, palmilla, palmillas, palmípeda, palmípedas, palmípedo, palmípedos, palmita, palmitas, palmitera, palmiteras, palmitiesa, palmitiesas, palmitieso, palmitiesos, palmito,
 *  palmitos, palmo, palmó, palmos, palmotada, palmotea, palmoteaba, palmoteabais, palmoteábamos, palmoteaban, palmoteabas, palmotead, palmoteada, palmoteadas, palmoteado, palmoteador, palmoteadores, palmoteados, palmoteáis, palmoteamos, palmotean, palmoteando, palmotear, palmoteara, palmoteará, palmotearais, palmoteáramos, palmotearan, palmotearán, palmotearas, palmotearás, palmoteare, palmotearé, palmoteareis, palmotearéis, palmotearemos, palmoteáremos, palmotearen, palmoteares, palmotearía, palmotearíais, palmotearíamos, palmotearían, palmotearías, palmotearon, palmoteas, palmotease, palmoteaseis, palmoteásemos, palmoteasen, palmoteases, palmoteaste, palmoteasteis, palmotee, palmoteé,
 *  palmoteéis, palmoteemos, palmoteen, palmotees, palmoteo, palmoteó, palmoteos, palo, paloduz, palomadura, palomaduras, palomar, palomares, palomariega, palomariegas, palomas, palomea, palomeaba, palomeabais, palomeábamos, palomeaban, palomeabas, palomead, palomeada, palomeadas, palomeado, palomeados, palomeáis, palomeamos, palomean, palomeando, palomear, palomeara, palomeará, palomearais, palomeáramos, palomearan, palomearán, palomearas, palomearás, palomeare, palomearé, palomeareis, palomearéis, palomearemos, palomeáremos, palomearen, palomeares, palomearía, palomearíais, palomearíamos, palomearían, palomearías, palomearon, palomeas, palomease, palomeaseis, palomeásemos,
 *  palomeasen, palomeases, palomeaste, palomeasteis, palomee, palomeé, palomeéis, palomeemos, palomeen, palomees, palomeo, palomeó, palomera, palomeras, palomería, palomerías, palomero, palomeros, palometa, palometas, palomilla, palomillas, palomina, palominas, palomino, palominos, palomita, palomitas, palomo, palomos, palón, palones, palor, palos, palotada, palotadas, palotazo, palotazos, palote, palotea, paloteaba, paloteabais, paloteábamos, paloteaban, paloteabas, palotead, paloteada,
 * paloteadas, paloteado, paloteados, paloteáis, paloteamos, palotean, paloteando, palotear, paloteara, paloteará, palotearais, paloteáramos, palotearan, palotearán, palotearas, palotearás, paloteare, palotearé, paloteareis, palotearéis, palotearemos, paloteáremos, palotearen, paloteares, palotearía, palotearíais, palotearíamos, palotearían, palotearías, palotearon, paloteas, palotease, paloteaseis, paloteásemos, paloteasen, paloteases, paloteaste, paloteasteis, palotee, paloteé, paloteéis, paloteemos, paloteen, palotees, paloteo, paloteó, paloteos, palotes, palpa, palpaba, palpabais, palpábamos, palpaban, palpabas, palpable, palpablemente, palpables, palpabls, palpación, palpaciones, palpad, palpada, palpadas, palpado, palpados,
 * palpadura, palpaduras, palpáis, palpallén, palpamiento, palpamientos, palpamos, palpan, palpando, palpándola, palpándolas, palpándole, palpándoles, palpándolo, palpándolos, palpándomelo, palpándonoslo, palpándooslo, palpándoselo, palpándotelo, palpar, palpara, palpará, palparais, palpáramos, palparan, palparán, palparas, palparás, palpare, palparé, palpareis, palparéis, palparemos, palpáremos, palparen, palpares, palparía, palparíais, palparíamos, palparían, palparías, palparla, palparlas, palparle, palparles, palparlo, palparlos, palparon, palpas, palpase, palpaseis, palpásemos, palpasen, palpases, palpaste, palpasteis, palpe, palpé, pálpebra, palpebral, palpéis, palpemos, palpen, palpes, palpi, palpita, palpitaba,
 * palpitabais, palpitábamos, palpitaban, palpitabas, palpitación, palpitaciones, palpitad, palpitada, palpitadas, palpitado, palpitados, palpitáis, palpitamos, palpitan, palpitando, palpitante, palpitantes, palpitar, palpitara, palpitará, palpitarais, palpitáramos, palpitaran, palpitarán, palpitaras, palpitarás, palpitare, palpitaré, palpitareis, palpitaréis, palpitaremos, palpitáremos, palpitaren, palpitares, palpitaría, palpitaríais, palpitaríamos, palpitarían, palpitarías, palpitaron, palpitas, palpitase, palpitaseis, palpitásemos, palpitasen, palpitases, palpitaste, palpitasteis, palpite, palpité, palpitéis, palpitemos, palpiten, palpites, palpito, palpitó, pálpito, palpo, palpó, palpos, palqui, palquista, palta, palto, palucha, paluchear, paluchería, paludamento,
 * paludamentos, palude, palúdica, palúdicas, palúdico, paludícola, palúdicos, paludismo, paludismos, paludo,
 * palumbario, palumbarios, palurda, palurdas, palurdo, palurdos, palustre, palustres, pamandabuán.
 *
 *
 * NO ME GUSTA QUE EN GITHUB APAREZCA HTML MAS UTILIZADO QUE JAVA
 *
 * */