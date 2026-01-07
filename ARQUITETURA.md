# Documentação Técnica da Arquitetura

## Padrão Arquitetural

### **Aspect-Oriented Programming (AOP)**

A arquitetura deste projeto utiliza AOP para separar responsabilidades transversais da lógica de negócio principal.

## Componentes do Sistema

### 1. **Camada de Domínio (Domain Layer)**

```
Conta (abstract)
  |
  +-- ContaCorrente (Cntc)
  +-- ContaPoupança (Cntp)  
  +-- ContaSalário (Cnts)
```

Cada classe representa um tipo diferente de conta bancária com comportamentos específicos.

### 2. **Camada de Aspectos (Aspect Layer)**

**VerificacaoSaldoAspect**

- **Tipo**: @Aspect
- **Método**: @Around
- **Pointcut**: `execution(void Conta.sacar(double))`

Intercepção de chamadas ao método `sacar()` para validação prévia do saldo.

### 3. **Camada de Aplicação (Application Layer)**

**main()**

- Instancia contas
- Executa operações de saque
- Registra resultados (sucesso/erro)

## Fluxo de Execução

```
1. Cliente chama: cntc.sacar(1000)
   |
   v
2. Aspecto intercepta a chamada
   |
   v
3. Verifica saldo
   |
   +-- Se saldo >= valor: permite execução (pjp.proceed())
   +-- Se saldo < valor: bloqueia e registra erro
```

## Tecnologias Utilizadas

- **Linguagem**: Java 8+
- **Framework**: AspectJ 1.9.7
- **Gerenciador de Dependências**: Maven 3.8.1
- **Compilador**: AspectJ Maven Plugin

## Vantagens da Arquitetura

✅ **Separação de Responsabilidades**: Lógica de validação separada do código principal
✅ **Reutilização**: Mesmo aspecto aplicado a múltiplas classes
✅ **Mantenibilidade**: Fácil de modificar aspectos sem alterar domínio
✅ **Escalabilidade**: Novos aspectos podem ser adicionados facilmente
✅ **Logging Centralizado**: Todos os erros registrados em um único lugar

## Desafios e Soluções

| Desafio | Solução |
|---------|----------|
| Compreender weaving | Documentar process de AspectJ | 
| Debugar aspectos | Usar logs e stacktraces |
| Performance | Otimizar pointcuts |

## Extensões Futuras

- [ ] Adicionar validação de déposito
- [ ] Implementar taxa de juro em poupança
- [ ] Integrar com banco de dados
- [ ] API REST para operações
- [ ] Testes automatizados

## Referências

- [AspectJ Official Docs](https://www.eclipse.org/aspectj/)
- [Spring Framework AOP](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
- [Maven AspectJ Plugin](https://www.mojohaus.org/aspectj-maven-plugin/)

---

**Ültima Atualização**: 2025-01-07
