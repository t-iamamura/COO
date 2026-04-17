public class ProdutoCor extends ProdutoDecorator {
    private String cor;

    public ProdutoCor(Produto p, String cor) {
        super(p);
        this.cor = cor;
    }

    @Override
    public String formataParaImpressao() {
        String textoBase;
        if (produto instanceof ProdutoDecorator) {
            textoBase = ((ProdutoDecorator) produto).formataParaImpressao();
        } else {
            textoBase = produto.getDescricao();
        }
        return "<font color=\"" + this.cor + "\">" + textoBase + "</font>";
    }
}