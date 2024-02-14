document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("formexercicio").addEventListener("submit", (evt) => {
        let formData = new FormData(document.getElementById("formexercicio"));

        if (formData.get("id").trim() == "") {
            methodType = "POST";
        }else{
            methodType = "PUT";
        }
        fetch("/exercicios", {
            method: methodType,
            headers: [
                ["Content-Type", "application/json"]
            ],
            credentials: "include",
            body: JSON.stringify(Object.fromEntries(formData))
        }).then(() => {
            getExercicios();
        }).catch(erro => {
            console.log("erro no submit");

        });
        document.getElementById("formexercicio").reset();
        
        evt.preventDefault();
    });

    getExercicios();
});


function getExercicios(){
    fetch("/exercicios", {
        method: "GET",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        response.json().then(exercicios=>{
            let table = limparTabela();
            table.innerHTML = 
                `<tr>
                    <th>Tipo</th>
                    <th>Descrição</th>
                    <th>Série</th>
                    <th>Repetição</th>
                    <th>Carga</th>
                    <th></th>
                </tr>`;
                exercicios.forEach((exercicio) => {
                var newRow = table.insertRow();
                newRow.innerHTML = `
                    <td>${exercicio.tipo}</td>
                    <td>${exercicio.descricao}</td>
                    <td>${exercicio.serie}</td>
                    <td>${exercicio.repeticao}</td>
                    <td>${exercicio.carga}</td>
                    <td>
                        <a href="#" onclick="return load('${exercicio.id}')">
                            <!--span style='font-size:40px;'>&#x270D;</span-->
                            <i class="fa-solid fa-pencil"></i>
                        </a>
                        <a href="#" onclick="return remove('${exercicio.id}')">
                            <i class="fa-solid fa-eraser"></i>
                        </a>
                    </td>
                `
            });    
        });        
    }).catch(erro => {
        console.log("erro no getExercicios");
    });
}

function limparTabela() {
    let table = document.getElementById("tableexercicio");
    var rowCount = table.rows.length;
    for (var x = rowCount - 1; x > 0; x--) {
        table.deleteRow(x);
    }
    return table;
}


function remove(id){
    fetch("/exercicios/"+id, {
        method: "DELETE",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        getExercicios();
    }).catch(erro => {
        console.log("erro no load");
    });
    
}


function load(id){
    fetch("/exercicios/"+id, {
        method: "GET",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        response.json().then(exercicio=>{
            document.getElementById("id").value = exercicio.id;
            document.getElementById("txtipo").value = exercicio.tipo;
            document.getElementById("txdescricao").value = exercicio.descricao;
            document.getElementById("txserie").value = exercicio.serie;
            document.getElementById("txrepeticao").value = exercicio.repeticao;
            document.getElementById("txcarga").value = exercicio.carga;
        })
    }).catch(erro => {
        console.log("erro no load");
    });
}