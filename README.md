# Chat-TS-SOA-JMS
Aplicação desenvolvida em Java utilizando o Apache River,OpenJMS, e SOA para utilização de um chat

Os serviços do Apache River e do OpenJMS devem ser iniciados antes da utilização do sistema.

O padrão utilizado para o desenvolvimento foi o MVC. Então os pacotes estão estruturados da forma orientada. Basta seguir o padrão que é possível encontrar as classes por suas responsabilidades.

A ordem de exucação padrão é listada abaixo:
1. Webserver.jar(PublishTopicMessageWebServerController)
2. Mediator.jar(MediatorController)
3. Spy.jar(SpyController)
4. ChatClient.jar(ChatClientController)

Link para github: https://github.com/jpsilvaj/Chat-TS-SOA-JMS

Ferramentas utilizadas:

Eclipse Luna – 4.4 - IDE

Git 1.8.3 – Controle de versão

Github – hospedar código fonte

Miglayout-4.0 - View

As libs utilizadas para implementação estão armazenadas dentro da pasta Libs, não é necessário realizar o download. Talvez seja preciso apenas adicioná-las ao build path
