const getPelicula = () => {
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/cine_java_war_exploded/pelicula'
    }).done(function(res){
        let content = "";

        for(let i = 0; i < res.length; i++){
            content += `
            <tr>
                <td>${ res[i].id }</td>
                <td>${ res[i].titulo }</td>
                <td>${ res[i].descripcion }</td>
                <td>${ res[i].sinopsis }</td>
                <td>${ res[i].rating }</td>
                <td>${ res[i].fecha_registro }</td>
                <td>${ res[i].fecha_actualizacion }</td>
                <td>${ res[i].estado }</td>
                <td>${ res[i].categoria }</td>
                <td>
                    <button title="Modificar" onclick="getPeliculaById(${ res[i].id })" type="button" data-bs-toggle="modal" data-bs-target="#modalModificarPelicula" class="btn btn-outline-warning"><i class="far fa-edit"></i></button>
                </td>
                <td>
                    <button title="Eliminar" onclick="deletePelicula(${ res[i].id })" class="btn btn-outline-danger"><i class="far fa-trash-alt"></i></button>
                </td>
            </tr>
        `;
        }
        $("#table2 > tbody").html(content);
    });
}

const insertPelicula = () => {
    let pelicula = new Object();
    pelicula.titulo = document.getElementById("titulo").value;
    pelicula.descripcion = document.getElementById("descripcion").value;
    pelicula.sinopsis = document.getElementById("sinopsis").value;
    pelicula.rating = document.getElementById("rating").value;
    pelicula.categoria = document.getElementById("categoriaFk").value;
    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: "http://localhost:8080/cine_java_war_exploded/pelicula/save",
        data: pelicula,
    }).done(function(res) {
        if(res==null){
            Swal.fire({
                icon: 'error',
                title: 'La pelicula no ha sido registrada',
                text: 'Hay un error en los datos. Verifíquelos'
            });
        }else{
            Swal.fire({
                icon: 'success',
                title: 'La película ha sido registrada',
                confirmButtonText: 'Ok'
            }).then((result) => {
                if (result.isConfirmed) {
                    getPelicula();
                }
            });
        }
    });
}

const updatePelicula = ()=>{
    let pelicula = new Object();
    pelicula.id = document.getElementById("id1").value;
    pelicula.titulo = document.getElementById("titulo1").value;
    pelicula.descripcion = document.getElementById("descripcion1").value;
    pelicula.sinopsis = document.getElementById("sinopsis1").value;
    pelicula.rating = document.getElementById("rating1").value;
    pelicula.categoria = document.getElementById("categoriaFk1").value;
    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: `http://localhost:8080/cine_java_war_exploded/pelicula/save/${pelicula.id}`,
        data: pelicula,
    }).done(function(res) {
        if(res==null){
            Swal.fire({
                icon: 'error',
                title: 'Película NO modificada',
                text: 'La película no fue modificada'
            });
        }else{
            Swal.fire({
                title: 'La película ha sido modificada',
                confirmButtonText: 'Ok',
                icon: 'success',
            }).then((result) => {
                if (result.isConfirmed) {
                    getPelicula();
                }
            });
        }
    });
}

const getPeliculaById=(id)=>{
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/cine_java_war_exploded/pelicula/${id}`
    }).done(function(res){
        document.getElementById("id1").value = res.id;
        document.getElementById("titulo1").value = res.titulo;
        document.getElementById("descripcion1").value = res.descripcion;
        document.getElementById("sinopsis1").value = res.sinopsis;
        document.getElementById("rating1").value = res.rating;
        document.getElementById("categoriaFk1").value = res.categoria;
    });
}

const deletePelicula=(id)=>{
    $.ajax({
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded",
        },
        url: `http://localhost:8080/cine_java_war_exploded/pelicula/delete/${id}`
    }).done(function(res){
        if(res==false){
            Swal.fire({
                icon: 'error',
                title: 'La película no ha sido deshabilitada',
                text: 'Verifica los datos'
            })
        }else{
            Swal.fire({
                title: 'La película ha sido deshabilitada',
                confirmButtonText: 'Ok',
                icon: 'success',
            }).then((result) => {
                if (result.isConfirmed) {
                    getPelicula();
                }
            })
        }
    });
}