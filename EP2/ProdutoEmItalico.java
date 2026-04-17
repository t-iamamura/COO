public class ProdutoEmItalico extends ProdutoDecorator {

    public ProdutoEmItalico(Produto produto) {
        super(produto);
    }

    @Override
    public String formataParaImpressao() {
        String textoBase;
        if (produto instanceof ProdutoDecorator) {
            textoBase = ((ProdutoDecorator) produto).formataParaImpressao();
        } else {
            textoBase = produto.getDescricao();
        }
        return "<i>" + textoBase + "</i>";
    }
}