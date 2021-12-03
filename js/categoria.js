const getCategoria = () => {
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/cine_java_war_exploded/categoria'
    }).done(function(res){
        let content = "";

        for(let i = 0; i < res.length; i++){
            content += `
            <tr>
                <td>${ res[i].id }</td>
                <td>${ res[i].nombre }</td>
                <td>
                    <button title="Modificar" onclick="getCategoriaById(${ res[i].id })" type="button" data-bs-toggle="modal" data-bs-target="#modalModificarCategoria" class="btn btn-outline-warning"><i class="far fa-edit"></i></button>
                </td>
                <td>
                    <button title="Eliminar" onclick="deleteCategoria(${ res[i].id })" class="btn btn-outline-danger"><i class="far fa-trash-alt"></i></button>
                </td>
            </tr>
        `;
        }
        $("#table > tbody").html(content);
    });
}

const insertCategoria = () => {
    let categoria = new Object();
    categoria.nombre = document.getElementById("nombre").value;
    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: "http://localhost:8080/cine_java_war_exploded/categoria/save",
        data: categoria,
    }).done(function(res) {
        if(res==null){
            Swal.fire({
                icon: 'error',
                title: 'La categoria no ha sido registrada',
                text: 'Hay un error en los datos. Verifíquelos'
            })
        }else{
            Swal.fire({
                icon: 'success',
                title: 'La categoria ha sido registrada',
                confirmButtonText: 'Ok'
            }).then((result) => {
                if (result.isConfirmed) {
                    getCategoria();
                }
            })
        }
    });
}

const updateCategoria = ()=>{
    let categoria = new Object();
    categoria.id = document.getElementById("idCategoria1").value;
    categoria.nombre = document.getElementById("nombre1").value;
    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: `http://localhost:8080/cine_java_war_exploded/categoria/save/${categoria.id}`,
        data: categoria,

    }).done(function(res) {
        if(res==null){
            Swal.fire({
                icon: 'error',
                title: 'Categoría NO modificada',
                text: 'La categoría no fue modificada'
            })
        }else{
            Swal.fire({
                title: 'La categoría ha sido modificada',
                confirmButtonText: 'Ok',
                icon: 'success',
            }).then((result) => {
                if (result.isConfirmed) {
                    getCategoria();
                }
            })
        }
    });
}

const getCategoriaById=(id)=>{
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/cine_java_war_exploded/categoria/${id}`
    }).done(function(res){
        document.getElementById("idCategoria1").value = res.id;
        document.getElementById("nombre1").value = res.nombre;
    });
}

const deleteCategoria=(id)=>{
    $.ajax({
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: `http://localhost:8080/cine_java_war_exploded/categoria/delete/${id}`
    }).done(function(res){
        if(res==false){
            Swal.fire({
                icon: 'error',
                title: 'La categoría no ha sido eliminada',
                text: 'Verifica los datos'
            })
        }else{
            Swal.fire({
                title: 'La categoría ha sido eliminada',
                confirmButtonText: 'Ok',
                icon: 'success',
            }).then((result) => {
                if (result.isConfirmed) {
                    getCategoria();
                }
            })
        }
    });
}