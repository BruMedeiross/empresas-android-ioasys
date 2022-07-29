# README #

Estes documento README tem como objetivo fornecer as informaÃ§Ãµes necessÃ¡rias para realização do projeto Empresas.

### O QUE FAZER ? ###

* Você deve realizar um fork deste repositório e, ao finalizar, enviar o link do seu repositório para a nossa equipe. Lembre-se, NÃƒO Ã© necessÃ¡rio criar um Pull Request para isso.

### ESCOPO DO PROJETO ###

* Deve ser criado um aplicativo Android utilizando linguagem Java ou Kotlin com as seguintes especificaÃ§Ãµes:
* Login e acesso de UsuÃ¡rio jÃ¡ registrado
	* Para o login usamos padrÃµes OAuth 2.0. Na resposta de sucesso do login a api retornarÃ¡ 3 custom headers (access-token, client, uid);
	* Para ter acesso as demais APIS precisamos enviar esses 3 custom headers para a API autorizar a requisiÃ§Ã£o;
* Listagem de Empresas
* Detalhamento de Empresas

### Informações Importantes ###

* Layout e recortes disponíveis no Zeplin (http://zeplin.io)
Login - teste_ioasys
Senha - ioasys123

* Integração disponivel a partir de uma collection para Postman (https://www.getpostman.com/apps) disponivel neste repositório.
* O `README.md` deve conter uma pequena justificativa de cada biblioteca adicionada ao projeto como dependência.
* O `README.md` deve conter tambem o que vocÃª faria se tivesse mais tempo.
* O `README.md` do projeto deve conter instruÃ§Ãµes de como executar a aplicaÃ§Ã£o
* Independente de onde conseguiu chegar no teste Ã© importante disponibilizar seu fonte para analisarmos.

### Dados para Teste ###

* Servidor: https://empresas.ioasys.com.br
* VersÃ£o da API: v1
* UsuÃ¡rio de Teste: testeapple@ioasys.com.br
* Senha de Teste : 12341234

### Dicas ###

* Para requisiÃ§Ã£o sugerimos usar a biblioteca Retrofit
* Para download e cache de imagens use a biblioteca Glide
* Para parse de Json use a biblioteca GSON

### Bonus ###

* Testes unitários, pode usar a ferramenta que você tem mais experiência, nos explique o que ele tem de bom.
* Usar uma arquitetura testavel. Ex: MVP, MVVM, Clean, etc.
* Material Design
* Utilizar alguma ferramenta de InjeÃ§Ã£o de DependÃªncia, Dagger, Koin e etc..
* Utilizar Rx, LiveData, Coroutines.
* Padrões de projetos
