# progetto-pr2-java
Traccia del Progetto per esame di programmazione 2 a.a. 2020/2021 corso di laurea informatica unipi.
Lo scopo del progetto è lo sviluppo di un componente software di supporto alla gestione e
l’analisi di una rete sociale (SocialNetwork) denominata MicroBlog.
La rete sociale consente di inviare messaggi di testo di breve lunghezza, con un massimo di 140 caratteri,
chiamati post. Gli utenti possono ‘seguire’ i post di altri utenti. Una persona è rappresentata dal nome sulla
rete sociale. Gli utenti della rete sociale non possono seguire se stessi.
Parte 1
Si richiede di progettare, realizzare e documentare il tipo di dato Post per rappresentare un post. Un post è
descritto da un insieme di informazioni:
• id: identificatore univoco del post
• author: utente della rete sociale che ha scritto il post
• text: testo (massimo 140 caratteri) del post
• timestamp: data e ora di invio del post
• likes: lista degli utenti della rete sociale che hanno messo un like al post
Si definisca la specifica completa del tipo di dato Post, introducendo i relativi metodi e fornendo le
motivazioni delle scelte effettuate. Possono essere aggiunte altre informazioni motivando opportunamente
la scelta.
Si definisca l’implementazione del tipo di dato Post.
Parte 2
Si richiede di progettare, realizzare e documentare il tipo di dato SocialNetwork per operare sulla rete
sociale MicroBlog. Come struttura di implementazione della rete sociale si richiede di utilizzare
Map<String, Set<String>>
In particolare, map[a] definisce l’insieme delle persone seguite nella rete sociale dall’utente a.
