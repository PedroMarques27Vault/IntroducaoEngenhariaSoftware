# Projeto_IES - "Tinderito"
## Como correr o projeto localmente
- Realizar `npm start` na pasta genLocation-Api para iniciar o node.js
- Correr um docker container
- Depois de correr o projeto pela primeira vez, os dados sql na pasta projeto_IES/db-docker devem ser colocados na bd.

**Utilizadores para testar mais rapidamente:**
- **Admin:** admin@ies.com | admin
- **Cliente:** ilanaDillard@gmail.com | xSq9n2Xz
- **Negócio:** 'warrenhallcoffeebar.com@gmail.com | 41X573Bk


## Roles:

- Team Manager: Inês Leite
- Product Owner: Luís Pereira
- Architect: Pedro Marques
- DevOps Master: Pedro Souto
- Developers: All

### Tipos de utilizador:
- Admin
- Negócio
- Cliente

### Features:
- Mensagens entre clientes
- Chamadas de video entre clientes (zoom API)
- Encontrar um match perto deles (sensor de localização)
- Empresas promovem encontros em restaurantes, descontos em lojas, até pode haver descontos por estar nesta app
- Utilizadores podem usar os descontos e afins que as empresas promovem

## User Stories:

### Persona 1 (Cliente):
**Como um cliente:**
- Quero criar conta, para usufruir das funcionalidades todas da app.
- Quero poder definir a minha identidade/personalidade através de um perfil personalizável, escolhendo os meus interesses, para expor a minha personalidade.
- Quero que a aplicação encontre pessoas na minha área com interesses semelhantes, para a minha consideração.
- Quero poder aceitar ou rejeitar as sugestões que me são apresentadas, para filtrar a ‘busca’.
- Quero ser informado quando dei ‘match’, para saber que posso iniciar conversa.
- Quero poder mandar mensagens às pessoas com quem dei ’match’, para as conhecer melhor.
- Quero poder fazer uma videochamada com essas pessoas também, para as conhecer ainda melhor.
- Quero receber ofertas e descontos, para que possa usufruir com/para as minhas ‘matches’.
- Quero poder filtrar as ofertas para se adequarem mais a mim.
- Quero poder clicar nas ofertas, para obter mais informação sobre ela.
- Quero poder denunciar comportamentos/perfis impróprios, para que possam ser removidos da aplicação

### Persona 2 (Negócio):
**Como um negócio:**
- Quero poder criar conta, para poder expor as minhas ofertas.
- Quero poder criar ofertas/promoções, para publicitar o meu negócio.
- Quero poder editar as minhas ofertas, para as atualizar.
- Quero que as minhas ofertas apareçam a clientes relevantes, para aumentar interesse no meu negócio.
- Quero poder ver quantas pessoas se interessaram na oferta, para observar o nível de apelo das minhas ofertas.

### Persona 3 (Admin):
**Como um administrador:**
- Quero poder ver uma lista de todos os utilizadores, para saber quem tem acesso à aplicação.
- Quero autenticar Negócios, para garantir a sua veracidade e a segurança dos clientes.
- Quero poder monitorizar pagamentos dos clientes, para garantir um lucro.
- Quero receber reports dos clientes sobre conteúdo impróprio, para analisar se é de facto um problema.
- Quero poder eliminar as contas impróprias, para manter a aplicação e os seus clientes seguros.

**NOTA:** O negócio seria cobrado uma mensalidade para publicitar na aplicação.



