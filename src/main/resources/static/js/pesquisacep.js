function limpa() {
	//Limpa valores do formulário de cep.
	document.getElementById('logradouro').value=("");
	document.getElementById('bairro').value=("");
	document.getElementById('localidade').value=("");
	document.getElementById('uf').value=("");
};

function retorno(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('logradouro').value=(conteudo.logradouro);
        document.getElementById('bairro').value=(conteudo.bairro);
        document.getElementById('localidade').value=(conteudo.localidade);
        document.getElementById('uf').value=(conteudo.uf);
    }
    else {
        limpa();
        alert("CEP não encontrado.");
    }
};
    
function pesquisa(valor) {
    var cep = valor.replace(/\D/g, '');

    if (cep != "") {
        var validacep = /^[0-9]{8}$/;
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('logradouro').value="...";
            document.getElementById('bairro').value="...";
            document.getElementById('localidade').value="...";
            document.getElementById('uf').value="...";

            var script = document.createElement('script');
            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=retorno';
            document.body.appendChild(script);
        }
        else {
            limpa();
            alert("Formato de CEP inválido.");
        }
    }
    else {
        limpa();
    }
};