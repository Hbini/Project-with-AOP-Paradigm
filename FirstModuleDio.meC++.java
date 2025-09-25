import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

public class BankAspectJExample {

    public static abstract class Conta {
        protected double saldo;
        protected String tpconta;

        public Conta(double saldinic, String tpconta) {
            this.saldo = saldinic;
            this.tpconta = tpconta;
        }

        public void sacar(double valor) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado na conta " + tpconta + ". Saldo restante: R$ " + saldo);
        }

        public double getSaldo() {
            return saldo;
        }

        public String getTpconta() {
            return tpconta;
        }
    }

    public static class Cntc extends Conta {
        public Cntc(double saldinic) {
            super(saldinic, "Corrente");
        }
    }

    public static class Cntp extends Conta {
        public Cntp(double saldinic) {
            super(saldinic, "Poupança");
        }
    }

    public static class Cnts extends Conta {
        public Cnts(double saldinic) {
            super(saldinic, "Salário");
        }
    }

    @Aspect
    public static class VerificacaoSaldoAspect {
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

    public static void main(String[] args) {
        Cntc cntc = new Cntc(1000);
        Cntp cntp = new Cntp(500);
        Cnts cnts = new Cnts(200);

        cntc.sacar(1500);  // Vai logar erro
        cntp.sacar(300);   // Vai permitir saque
        cnts.sacar(250);   // Vai logar erro
    }
}
