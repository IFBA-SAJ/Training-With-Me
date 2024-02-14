document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("formcliente").addEventListener("submit", (evt) => {
        let formData = new FormData(document.getElementById("formcliente"));

        if (formData.get("id").trim() == "") {
            methodType = "POST";
        }else{
            methodType = "PUT";
        }
        fetch("/clientes", {
            method: methodType,
            headers: [
                ["Content-Type", "application/json"]
            ],
            credentials: "include",
            body: JSON.stringify(Object.fromEntries(formData))
        }).then(() => {
            getClientes();
        }).catch(erro => {
            console.log("erro no submit");

        });
        document.getElementById("formcliente").reset();

        evt.preventDefault();
    });

    getClientes();
});


function getClientes(){
    fetch("/clientes", {
        method: "GET",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        response.json().then(clientes=>{
            let table = limparTabela();
            table.innerHTML = 
                `<tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Idade</th>
                    <th>Anamnese</th>
                    <th></th>
                </tr>`;
            clientes.forEach((cliente) => {
                var newRow = table.insertRow();
                newRow.innerHTML = `
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.idade}</td>
                    <td>${cliente.anamnese}</td>
                    <td>
                        <a href="#" onclick="return load('${cliente.id}')">
                            <!--span style='font-size:40px;'>&#x270D;</span-->
                            <i class="fa-solid fa-pencil"></i>
                        </a>
                        <a href="#" onclick="return remove('${cliente.id}')">
                            <i class="fa-solid fa-eraser"></i>
                        </a>
                    </td>
                `
            });    
        });        
    }).catch(erro => {
        console.log("erro no getClientes");
    });
}

function limparTabela() {
    let table = document.getElementById("tablecliente");
    var rowCount = table.rows.length;
    for (var x = rowCount - 1; x > 0; x--) {
        table.deleteRow(x);
    }
    return table;
}

//chamar o servidor para excluir o cliente e atualizar a tabela
function remove(id){
    fetch("/clientes/"+id, {
        method: "DELETE",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        getClientes();
    }).catch(erro => {
        console.log("erro no load");
    });
    
}

//chamar o servidor para obter os valores o cliente selecionado
//atualizar o formulario com esses valores
function load(id){
    fetch("/clientes/"+id, {
        method: "GET",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        response.json().then(cliente=>{
            document.getElementById("id").value = cliente.id;
            document.getElementById("txnome").value = cliente.nome;
            document.getElementById("txemail").value = cliente.email;
            document.getElementById("txidade").value = cliente.idade;
            document.getElementById("txanamnese").value = cliente.anamnese;
            document.getElementById("txaltura").value = cliente.altura;
            document.getElementById("txpeso").value = cliente.peso;
            document.getElementById("tximc").value = cliente.imc;
        })
    }).catch(erro => {
        console.log("erro no load");
    });
}