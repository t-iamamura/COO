public class ProdutoEmNegrito extends ProdutoDecorator {

    public ProdutoEmNegrito(Produto produto) {
        super(produto);
    }

    @Override
    public String formataParaImpressao() {
        String textoBase;

        // Verifica se o objeto que estamos decorando é OUTRO decorador
        if (produto instanceof ProdutoDecorator) {
            // Se for, continua a chamada em cadeia para obter o texto já formatado
            textoBase = ((ProdutoDecorator) produto).formataParaImpressao();
        } else {
            // Se não for, significa que estamos no fim da linha (é um ProdutoPadrao).
            // Pegamos a descrição original usando o getter padrão.
            textoBase = produto.getDescricao();
        }

        // Aplica a formatação <b> ao texto base
        return "<b>" + textoBase + "</b>";
    }
}