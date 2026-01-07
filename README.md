# Projeto com Paradigma de Programação Orientado à Aspectos (AOP)

## 📋 Descrição do Projeto

Este projeto implementa uma solução de **sistema bancário** utilizando o paradigma de **Programação Orientada a Aspectos (AOP)** com **Java e AspectJ**. A solução valida o saldo de uma conta antes de permitir saques, separando a lógica de verificação (aspecto transversal) do código principal.

## 🎯 Objetivo

Demonstrar como aplicar a Programação Orientada a Aspectos para:
- Separar responsabilidades transversais (validação de saldo)
- Manter o código principal limpo e desacoplado
- Reutilizar lógica de validação em múltiplas classes
- Facilitar manutenção e evolução do código

## 📁 Estrutura do Projeto

```
Project-with-AOP-Paradigm/
├── FirstModuleDio.meC++.java    # Código principal com classes e aspectos
├── README.md                     # Este arquivo
├── ARQUITETURA.md               # Documentação detalhada da arquitetura
└── pom.xml                      # Dependências Maven (AspectJ)
```

## 🔧 Componentes Principais

### 1. Classe Abstrata `Conta`

Representa uma conta bancária genérica com:
- **Atributos**:
  - `saldo`: valor em reais (double)
  - `tpconta`: tipo da conta (String)
- **Métodos**:
  - `sacar(double valor)`: realiza o saque
  - `depositar(double valor)`: realiza o depósito
  - `getSaldo()`: retorna o saldo atual
  - `getTpconta()`: retorna o tipo de conta

### 2. Classes Filhas

**ContaCorrente (Cntc)**
```java
public class Cntc extends Conta {
  public Cntc(double saldinic) {
    super(saldinic, "Corrente");
  }
}
```

**ContaPoupança (Cntp)**
```java
public class Cntp extends Conta {
  public Cntp(double saldinic) {
    super(saldinic, "Poupança");
  }
}
```

**ContaSalário (Cnts)**
```java
public class Cnts extends Conta {
  public Cnts(double saldinic) {
    super(saldinic, "Salário");
  }
}
```

### 3. Aspecto `VerificacaoSaldoAspect`

O aspecto transversal que intercede na execução de saques:

```java
@Aspect
public class VerificacaoSaldoAspect {
  @Around("execution(void BankAspectJExample.Conta.sacar(double)) && args(valor) && target(conta)")
  public void verificarSaldo(ProceedingJoinPoint pjp, double valor, Conta conta) throws Throwable {
    if (conta.getSaldo() < valor) {
      System.err.println("[Erro] Saldo insuficiente para o saque de R$ " + valor +
        ". Saldo atual: R$ " + conta.getSaldo() +
        " na conta " + conta.getTpconta());
    } else {
      pjp.proceed();
    }
  }
}
```

**Como funciona:**
1. O `@Around` intercepta todas as chamadas a `sacar()`
2. Verifica se o saldo é suficiente
3. Se SIM: permite a execução (`pjp.proceed()`)
4. Se NÃO: loga um erro sem permitir o saque

## 🚀 Como Usar

### Pré-requisitos
- Java 8+
- Maven (para gerenciar dependências AspectJ)
- IDE (Eclipse, IntelliJ ou VS Code)

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/Hbini/Project-with-AOP-Paradigm.git
cd Project-with-AOP-Paradigm
```

2. Adicione dependências ao `pom.xml`:
```xml
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjrt</artifactId>
  <version>1.9.7</version>
</dependency>
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjweaver</artifactId>
  <version>1.9.7</version>
</dependency>
```

3. Compile e execute:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="BankAspectJExample"
```

### Exemplo de Execução

```bash
$ java -javaagent:lib/aspectjweaver.jar BankAspectJExample

Saque de R$ 1000 realizado na conta Salário. Saldo restante: R$ 900
[Erro] Saldo insuficiente para o saque de R$ 1500. Saldo atual: R$ 500 na conta Poupança
Saque de R$ 250 realizado na conta Corrente. Saldo restante: R$ 250
```

## 📊 Diagrama de Fluxo

```
┌─────────────┐
│   Cliente   │
└──────┬──────┘
       │
       ├─ cntc.sacar(1000)  ✓ Permitido
       │
       ├─ cntp.sacar(1500)  ✗ Bloqueado (saldo insuficiente)
       │
       └─ cnts.sacar(250)   ✓ Permitido

┌────────────────────────────────────────┐
│   VerificacaoSaldoAspect (AOP)         │
│  Valida em TEMPO DE EXECUÇÃO           │
└────────────────────────────────────────┘
```

## 🎓 Conceitos AOP Aplicados

### 1. **Aspecto (@Aspect)**
Classe que encapsula o comportamento transversal (validação de saldo)

### 2. **JoinPoint**
Pontos no código onde o aspecto pode ser aplicado (todos os `sacar()`)

### 3. **Advice (@Around)**
Código executado quando um JoinPoint é interceptado

### 4. **Pointcut**
Expressão que especifica quais JoinPoints devem ser interceptados

### 5. **Weaving**
Processo de aplicar aspectos ao código (em tempo de compilação ou execução)

## 💡 Melhorias Implementadas

✅ Separação de responsabilidades com AOP  
✅ Validação transversal em múltiplas contas  
✅ Código principal limpo e desacoplado  
✅ Fácil manutenção e evolução  
✅ Reutilização de lógica de validação  
✅ Logging de erros centralizado  

## 🔗 Links Úteis

- [AspectJ Documentation](https://www.eclipse.org/aspectj/)
- [Spring AOP Guide](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
- [Maven AspectJ Plugin](https://www.mojohaus.org/aspectj-maven-plugin/)

## 📝 Licença

Este projeto é de código aberto e disponível para fins educacionais.

## 👤 Autor

**Bryan Kira** (Repositório Base)  
**Hbini** (Fork e Melhorias)

---

**Desenvolvido para o Desafio de Projeto da DIO - Formação CC Developer**
