# Eclipse RPC Plugin 🎮

Um plugin leve para Eclipse que mostra no seu Discord o que você está programando — nome do projeto, arquivo, linha atual e tudo isso em tempo real!

## 🧩 Funcionalidades

- 📁 Mostra nome do projeto, arquivo e linha atual e até uma imagem!
- 🖥️ Interface de configurações nativa no Eclipse (Preferences)
- 🌍 Suporte a múltiplos idiomas (i18n)
- ⚡ Super leve e roda em segundo plano (assíncrono)
- 📦 Inclui tudo necessário para compilar, sem dor de cabeça

---

## 🚀 Como compilar

1. Clone este repositório:
   ```bash
   git clone https://github.com/PyEdoardo/eclipse-rpc.git
Abra no Eclipse como um projeto de plugin (ou Java normal, se preferir)

Compile e gere o .jar pela sua IDE ou via terminal usando:

- bash

- javac -d bin src/**/*.java
- jar cf EclipseRPCPlugin.jar -C bin .

Ou você pode via o eclipse exportar o plugin diretamente:
   - Vá em `File` > `Export...`
   - Selecione `Java` > `JAR file`
   - Escolha o destino e clique em `Finish`

## ⬇️ Download Rápido
Se quiser só usar sem compilar, é só ir na aba de Releases e baixar o .jar mais recente.

## 🏳️ Instalação fácil
Após compilar o jar ou baixar via releases, coloque o arquivo .jar na pasta `dropins` do Eclipse. O Eclipse vai reconhecer automaticamente o plugin ao ser reiniciado.

## 🛠️ Requisitos
	- Java 8+
	- Eclipse IDE
	- Discord com Rich Presence ativado
	
## 📝 Notas sobre o plugin
Às vezes o idioma pode demorar um pouco para mudar após ser trocado nas configurações. Isso acontece provavelmente por conta de uma pequena lentidão de I/O ao carregar os arquivos de tradução ou algum atraso no pipe entre o Eclipse e o Discord. Mas nada que atrapalhe demais: só de abrir novamente a janela de configurações o idioma já se atualiza corretamente. 😉
Irei corrigir isso em breve, mas por enquanto é só um detalhe.

## ⚠️ Observações Técnicas
	- Este projeto só compila corretamente com Java 8, então certifique-se de estar usando essa versão na sua IDE ou pipeline.

	- Ainda faltam algumas imagens do Rich Presence, pois estou estudando e finalizando os testes aos poucos.

	- A tradução também está sendo concluída gradualmente, então algumas partes podem não estar traduzidas 100%, mas o essencial já está funcionando bem.

## 📃 Licença
Este projeto é licenciado sob a Apache License.

## ☕ Bibliotecas vendorizadas
- [Google GSON](https://github.com/google/gson)
- [Discord Game SDK4J](https://github.com/JnCrMx/discord-game-sdk4j)