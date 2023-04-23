public class Context {

    // private Strategy strategy ; // = null
    private Strategy strategy = new DefaultStrategyImp();
    public void effectuerStrategy(){
        System.out.println("********************************");
        strategy.operationStrategy();
        System.out.println("================================");
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
