PGDMP     0    -                z         	   lostbeach #   12.9 (Ubuntu 12.9-0ubuntu0.20.04.1) #   12.9 (Ubuntu 12.9-0ubuntu0.20.04.1) $    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    32768 	   lostbeach    DATABASE     {   CREATE DATABASE lostbeach WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE lostbeach;
                fpw    false            ?            1259    57385    bagnino    TABLE     ?   CREATE TABLE public.bagnino (
    nome character varying(50),
    cognome character varying(50),
    telefono character varying(20),
    email character varying(50),
    attestati character varying(250)
);
    DROP TABLE public.bagnino;
       public         heap    fpw    false            ?            1259    32803    fattura    TABLE     ?   CREATE TABLE public.fattura (
    id integer NOT NULL,
    prezzo bigint,
    data date,
    posti bigint,
    descrizione character varying(250),
    utente_id character varying(20),
    bagnino_id character varying(101)
);
    DROP TABLE public.fattura;
       public         heap    fpw    false            ?            1259    32801    fattura_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.fattura_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.fattura_id_seq;
       public          fpw    false    208            ?           0    0    fattura_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.fattura_id_seq OWNED BY public.fattura.id;
          public          fpw    false    207            ?            1259    49193 	   messaggio    TABLE     f   CREATE TABLE public.messaggio (
    username character varying(20),
    mex character varying(250)
);
    DROP TABLE public.messaggio;
       public         heap    fpw    false            ?            1259    32776    prenotazione    TABLE     ?   CREATE TABLE public.prenotazione (
    id integer NOT NULL,
    cliente character varying(20),
    orario character varying(10),
    data date,
    posti bigint,
    slot bigint,
    data_di_prenotazione date
);
     DROP TABLE public.prenotazione;
       public         heap    fpw    false            ?            1259    32774    prenotazione_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.prenotazione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.prenotazione_id_seq;
       public          fpw    false    204            ?           0    0    prenotazione_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.prenotazione_id_seq OWNED BY public.prenotazione.id;
          public          fpw    false    203            ?            1259    32789    slot    TABLE     ?   CREATE TABLE public.slot (
    id integer NOT NULL,
    orario character varying(10),
    data date,
    posti bigint,
    bagnino character varying(101)
);
    DROP TABLE public.slot;
       public         heap    fpw    false            ?            1259    32787    slot_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.slot_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.slot_id_seq;
       public          fpw    false    206            ?           0    0    slot_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.slot_id_seq OWNED BY public.slot.id;
          public          fpw    false    205            ?            1259    32769    utente    TABLE     ?  CREATE TABLE public.utente (
    username character varying(20) NOT NULL,
    password character varying(20),
    nome character varying(50),
    cognome character varying(50),
    data_di_nascita date,
    sesso character varying(20),
    email character varying(50),
    scelta_fattura character varying(2),
    codice_fiscale character varying(20),
    telefono character varying(20),
    amministratore boolean
);
    DROP TABLE public.utente;
       public         heap    fpw    false            (           2604    32806 
   fattura id    DEFAULT     h   ALTER TABLE ONLY public.fattura ALTER COLUMN id SET DEFAULT nextval('public.fattura_id_seq'::regclass);
 9   ALTER TABLE public.fattura ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    207    208    208            &           2604    32779    prenotazione id    DEFAULT     r   ALTER TABLE ONLY public.prenotazione ALTER COLUMN id SET DEFAULT nextval('public.prenotazione_id_seq'::regclass);
 >   ALTER TABLE public.prenotazione ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    203    204    204            '           2604    32792    slot id    DEFAULT     b   ALTER TABLE ONLY public.slot ALTER COLUMN id SET DEFAULT nextval('public.slot_id_seq'::regclass);
 6   ALTER TABLE public.slot ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    206    205    206            ?          0    57385    bagnino 
   TABLE DATA           L   COPY public.bagnino (nome, cognome, telefono, email, attestati) FROM stdin;
    public          fpw    false    210   ?(       ?          0    32803    fattura 
   TABLE DATA           ^   COPY public.fattura (id, prezzo, data, posti, descrizione, utente_id, bagnino_id) FROM stdin;
    public          fpw    false    208   `)       ?          0    49193 	   messaggio 
   TABLE DATA           2   COPY public.messaggio (username, mex) FROM stdin;
    public          fpw    false    209   **       ?          0    32776    prenotazione 
   TABLE DATA           d   COPY public.prenotazione (id, cliente, orario, data, posti, slot, data_di_prenotazione) FROM stdin;
    public          fpw    false    204   ?*       ?          0    32789    slot 
   TABLE DATA           @   COPY public.slot (id, orario, data, posti, bagnino) FROM stdin;
    public          fpw    false    206   7+       ?          0    32769    utente 
   TABLE DATA           ?   COPY public.utente (username, password, nome, cognome, data_di_nascita, sesso, email, scelta_fattura, codice_fiscale, telefono, amministratore) FROM stdin;
    public          fpw    false    202   ?+       ?           0    0    fattura_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.fattura_id_seq', 20, true);
          public          fpw    false    207            ?           0    0    prenotazione_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.prenotazione_id_seq', 45, true);
          public          fpw    false    203            ?           0    0    slot_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.slot_id_seq', 26, true);
          public          fpw    false    205            0           2606    32808    fattura fattura_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.fattura
    ADD CONSTRAINT fattura_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.fattura DROP CONSTRAINT fattura_pkey;
       public            fpw    false    208            ,           2606    32781    prenotazione prenotazione_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.prenotazione
    ADD CONSTRAINT prenotazione_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.prenotazione DROP CONSTRAINT prenotazione_pkey;
       public            fpw    false    204            .           2606    32794    slot slot_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.slot
    ADD CONSTRAINT slot_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.slot DROP CONSTRAINT slot_pkey;
       public            fpw    false    206            *           2606    32773    utente utente_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (username);
 <   ALTER TABLE ONLY public.utente DROP CONSTRAINT utente_pkey;
       public            fpw    false    202            3           2606    41001    fattura fattura_utente_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.fattura
    ADD CONSTRAINT fattura_utente_id_fkey FOREIGN KEY (utente_id) REFERENCES public.utente(username) ON UPDATE CASCADE;
 H   ALTER TABLE ONLY public.fattura DROP CONSTRAINT fattura_utente_id_fkey;
       public          fpw    false    2858    208    202            4           2606    57388 !   messaggio messaggio_username_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.messaggio
    ADD CONSTRAINT messaggio_username_fkey FOREIGN KEY (username) REFERENCES public.utente(username) ON UPDATE CASCADE;
 K   ALTER TABLE ONLY public.messaggio DROP CONSTRAINT messaggio_username_fkey;
       public          fpw    false    2858    209    202            1           2606    32782 &   prenotazione prenotazione_cliente_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.prenotazione
    ADD CONSTRAINT prenotazione_cliente_fkey FOREIGN KEY (cliente) REFERENCES public.utente(username) ON UPDATE CASCADE;
 P   ALTER TABLE ONLY public.prenotazione DROP CONSTRAINT prenotazione_cliente_fkey;
       public          fpw    false    2858    204    202            2           2606    32809 #   prenotazione prenotazione_slot_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.prenotazione
    ADD CONSTRAINT prenotazione_slot_fkey FOREIGN KEY (slot) REFERENCES public.slot(id) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.prenotazione DROP CONSTRAINT prenotazione_slot_fkey;
       public          fpw    false    2862    206    204            ?   r   x?u?A
?0??ur?`??Af?"???4??? ?d???{?UX?t??? ???s!?krO??5D	a??J?W?qSf5WKo?-0?ӵ~??????s?OYRc?D?H?      ?   ?   x???K
1??uz?????׌w<??8S$0?H????]??@???+.??,??1I?K??2%?'??*????k???'%eY??J?Dx;?B??ꖐ?|??????????;?f????Q?p?????u3Ȥ??_?|???k???c*T՟??E??QY?F?Z??i!??c?E???      ?   T   x?+.-??0???T((=?|h?BJbQ?Bi^?Bqr~^I?=WqbQ??!gA~IQj?BbY*X>Q?8?(13_!3O? ?Tݞ+F??? ?zf      ?   ?   x?u?K
?0D?㻸X?ۇ(? ݈?!q ?Eo_RpZ???4?Y?u__??1O???!?`ì??Ƣ?sf?AوU1??l[?r?.+v????ͣ,T??A?U!?q?d??&!~??naA?}??t?g????????
?jb????M)??E      ?   ?   x?m?1?0Eg??@P?????XmUE???????????????ʵ????uY????܍\*B??u.iYR???? D
$??#??c??3??[?Rv?C+?dA?tV??:???????Ը|???I_??-쀂*???BR???P?h??x??OƘ??r?      ?   <  x?m??n?0ƯO????c?ݜ?eN?ƛ???څ??o?lf'???????t'???@&$W?????׭F???·?? E$)?)2x>4???_M$??6??rxEQl?[)?Z?ݎQ???#??r?nI8?*??f??##?JXj_}???_@?`5_???"˲?g ?
??T??`?Z*?:?W	?w J????ޕ?v??}o??̖O9???.V???q!R?x????H?ik??BZe?)Ҕ??b?G?&?MQLC
??9??pT?>?Q8?????^|?ڌtc?e"?P?޿I呂Q?9??]?I?$?V??     