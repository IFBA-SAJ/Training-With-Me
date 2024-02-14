document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("formprofessor").addEventListener("submit", (evt) => {
        let formData = new FormData(document.getElementById("formprofessor"));

        if (formData.get("id").trim() == "") {
            methodType = "POST";
        }else{
            methodType = "PUT";
        }
        fetch("/professores", {
            method: methodType,
            headers: [
                ["Content-Type", "application/json"]
            ],
            credentials: "include",
            body: JSON.stringify(Object.fromEntries(formData))
        }).then(() => {
            getProfessores();
        }).catch(erro => {
            console.log("erro no submit");

        });
        document.getElementById("formprofessor").reset();
        
        evt.preventDefault();
    });

    getProfessores();
});


function getProfessores(){
    fetch("/professores", {
        method: "GET",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        response.json().then(professores=>{
            let table = limparTabela();
            table.innerHTML = 
                `<tr>
                    <th>CREF</th>
                    <th>Nome</th>
                    <th>Idade</th>
                    <th></th>
                </tr>`;
                professores.forEach((professor) => {
                var newRow = table.insertRow();
                newRow.innerHTML = `
                    <td>${professor.cref}</td>
                    <td>${professor.nome}</td>
                    <td>${professor.idade}</td>
                    <td>
                        <a href="#" onclick="return load('${professor.id}')">
                            <!--span style='font-size:40px;'>&#x270D;</span-->
                            <i class="fa-solid fa-pencil"></i>
                        </a>
                        <a href="#" onclick="return remove('${professor.id}')">
                            <i class="fa-solid fa-eraser"></i>
                        </a>
                    </td>
                `
            });    
        });        
    }).catch(erro => {
        console.log("erro no getProfessores");
    });
}

function limparTabela() {
    let table = document.getElementById("tableprofessor");
    var rowCount = table.rows.length;
    for (var x = rowCount - 1; x > 0; x--) {
        table.deleteRow(x);
    }
    return table;
}


function remove(id){
    fetch("/professores/"+id, {
        method: "DELETE",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        getProfessores();
    }).catch(erro => {
        console.log("erro no load");
    });
    
}


function load(id){
    fetch("/professores/"+id, {
        method: "GET",
        headers: [
            ["Content-Type", "application/json"]
        ],
        credentials: "include"
    }).then(response => {
        response.json().then(professor=>{
            document.getElementById("id").value = professor.id;
            document.getElementById("txcref").value = professor.cref;
            document.getElementById("txcpf").value = professor.cpf;
            document.getElementById("txnome").value = professor.nome;
            document.getElementById("txemail").value = professor.email;
            document.getElementById("txidade").value = professor.idade;
        })
    }).catch(erro => {
        console.log("erro no load");
    });
}

