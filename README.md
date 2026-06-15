# 🍻 Rachadinha - Divisor de Contas

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-Lollipop%205.0+-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Material Design](https://img.shields.io/badge/Material%20Design-3-757575?logo=materialdesign&logoColor=white)](https://m3.material.io/)

O **Rachadinha** é um aplicativo Android desenvolvido para resolver aquele momento clássico de "quem paga o quê" no final do bar ou restaurante. Com uma interface limpa e intuitiva, o app calcula gorjetas e divide o valor total da conta entre os amigos de forma instantânea.

---

## 🚀 Funcionalidades

- **Cálculo em Tempo Real**: Os valores são atualizados automaticamente enquanto você digita.
- **Gorjetas Customizadas**: Seleção rápida entre 10%, 15% ou 20% de gratificação para o garçom.
- **Divisão por Pessoa**: Informe o número de participantes e veja o valor exato para cada um.
- **Formatação de Moeda**: Exibição automática no padrão brasileiro (R$ 0,00).

## 🛠️ Diferenciais Técnicos

Este projeto foi construído seguindo as melhores práticas de desenvolvimento Android:

1.  **Validação Rigorosa**: O app é à prova de falhas. Ele impede crashes se o usuário digitar caracteres inválidos ou tentar dividir a conta por zero.
2.  **View Binding**: Utilização de View Binding para uma interação mais segura e eficiente com os componentes do layout XML.
3.  **UI Fluida**: Interface construída com `ConstraintLayout` para garantir que o design se adapte a diferentes tamanhos de tela.
4.  **UX Otimizada**: Uso de `ScrollView` para garantir acessibilidade mesmo quando o teclado numérico está aberto.

## 📱 Capturas de Tela

<div align="center">
  <img src="https://raw.githubusercontent.com/marllon-anisio/Rachadinha/main/screenshots/main_screen.png" width="300" alt="Tela Principal do App">
</div>

---

## 🏗️ Como Rodar o Projeto

### Pré-requisitos
- Android Studio Ladybug ou superior.
- JDK 11 ou superior.
- Um dispositivo físico ou emulador com Android 5.0 (API 24) ou superior.

### Passo a passo
1. Clone este repositório:
   ```bash
   git clone https://github.com/marllon-anisio/rachadinha.git
   ```
2. Abra o projeto no **Android Studio**.
3. Aguarde a sincronização do **Gradle**.
4. Clique em **Run** (ícone de play verde) para instalar no seu dispositivo.

---

## 💻 Tecnologias Utilizadas

- **Linguagem**: [Kotlin](https://kotlinlang.org/)
- **Layout**: XML (ConstraintLayout)
- **UI Components**: Material Components para Android
- **Build System**: Gradle Kotlin DSL (.kts)

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
<div align="center">
  Desenvolvido por <strong>Marllon Anísio</strong> 🚀
</div>
