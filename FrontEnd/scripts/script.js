document.addEventListener("DOMContentLoaded", () => {
    const cochesList = document.querySelector(".coches-list");
    const dateFilter = document.getElementById("date-filter");
    let cochesData = [];
    let isSortedRecent = true; 

    loadCoches();

    // Función para cargar los coches y mostrarlos.
    function loadCoches() {
        dateFilter.value = 'recientes';

        fetch("http://localhost:8080/coches")
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Error al obtener los coches");
                }
            })
            .then((data) => {
                // Almacenar los datos de los coches.
                cochesData = data; 
                renderCoches(data);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    // Función para renderizar los coches en la tabla.
    function renderCoches(coches) {
        cochesList.innerHTML = ""; 

        coches.forEach((coche) => {
            const row = cochesList.insertRow();
            row.insertCell(0).textContent = coche.marca;
            row.insertCell(1).textContent = coche.matricula;
            
            const precioCell = row.insertCell(2);
            precioCell.textContent = coche.precioVenta;
            precioCell.addEventListener('dblclick', () => {
                if(coche.estado == 'Disponible'){
                    makePriceEditable(precioCell, coche);
                }
                
            });

            const actionsCell = row.insertCell(3);
            const detailsButton = document.createElement('button');
            detailsButton.className = 'button-details';
            detailsButton.textContent = '+';
            detailsButton.addEventListener('click', () => {
                if (detailsButton.textContent == '+') {
                    showDetails(coche, detailsButton, row);
                } else if (detailsButton.textContent == '-') {
                    closeDetails(row.nextElementSibling, detailsButton, coches);
                }
            });
            actionsCell.appendChild(detailsButton);
        });
    }

    // Función para mostrar detalles de un coche.
    function showDetails(coche, detailsButton, row) {
        detailsButton.textContent = '-';
        const detailsRow = cochesList.insertRow(row.rowIndex);
        detailsRow.className = 'coche-details-row';
        const detailsCell = detailsRow.insertCell(0);
        detailsCell.colSpan = 4;

        const detailsContainer = document.createElement('div');
        detailsContainer.className = 'coche-details';

         // Crear una columna para los detalles en la parte izquierda.
        const detailsLeft = document.createElement('div');
        detailsLeft.className = 'details-left';

        const detalles = [
            { title: 'ID', value: coche.id },
            { title: 'Coste', value: coche.coste },
            { title: 'Fecha de Ingreso', value: coche.fechaIngreso },
            { title: 'Estado', value: coche.estado},
            { title: 'Concesionario', value: coche.concesionario.direccion }
        ];

        let valueDivEstado;

        detalles.forEach((detalle) => {
            const detailDiv = document.createElement('div');
            detailDiv.className = 'details-column';

            const titleDiv = document.createElement('div');
            titleDiv.className = 'detail-title';
            titleDiv.textContent = detalle.title;

            const valueDiv = document.createElement('div');
            valueDiv.className = 'detail-value';
            valueDiv.textContent = detalle.value;

            if (detalle.title == 'Estado') {
                valueDivEstado = valueDiv; 
            }

            detailDiv.appendChild(titleDiv);
            detailDiv.appendChild(valueDiv);

            detailsLeft.appendChild(detailDiv);
        });

        // Crear una columna para los botones en la parte derecha.
        const detailsRight = document.createElement('div');
        detailsRight.className = 'details-right';

        const venderButton = document.createElement('button');
        venderButton.className = 'button-vender';
        venderButton.textContent = 'Vender';
        venderButton.addEventListener('click', () => {        
                marcarVendido(coche);                          
        });

        const bajaAltaButton = document.createElement('button');
        bajaAltaButton.className = 'button-baja';  

        // Funcionamiento boton dinamico para dar de baja o alta.
        if (coche.estado == 'No Disponible') {
            bajaAltaButton.textContent = 'Alta';
            bajaAltaButton.addEventListener('click', () => {
                darDeAlta(coche);              
            });
        } else {
            bajaAltaButton.textContent = 'Baja';
            bajaAltaButton.addEventListener('click', () => {
                darDeBaja(coche);                
            });
        }
        
        detailsRight.appendChild(venderButton);
        detailsRight.appendChild(bajaAltaButton);
    
        // Agregar las columnas izquierda y derecha al recuadro de detalles.
        detailsContainer.appendChild(detailsLeft);
        detailsContainer.appendChild(detailsRight);
    
        detailsCell.appendChild(detailsContainer);
   
}

    // Funcion para cambiar el estado de un coche a vendido, 
    // solo se podra vender un coche disponible.
    function marcarVendido(coche){
        if (coche.estado == 'Disponible') {
            // Enviar solicitud para marcar como vendido.
            fetch(`http://localhost:8080/coches/marcar-como-vendido/${coche.id}`, {
                method: 'PUT',
                })
                .then(response => {
                    if (response.ok) {  
                        loadCoches();                
                    }
                })
                .catch(error => {
                    console.error('Error al marcar como vendido:', error);
                });
            }           
    }

    // Función para dar de baja un coche, 
    // solo se podrá dar de baja un coche disponible.
    function darDeBaja(coche) {
        if (coche.estado == 'Disponible') {
            // Enviar solicitud para dar de baja.
            fetch(`http://localhost:8080/coches/dar-de-baja/${coche.id}`, {
                method: 'PUT',
            })
                .then(response => {
                    if (response.ok) {
                        loadCoches();
                    }
                })
                .catch(error => {
                    console.error('Error al dar de baja:', error);
                });
        }       
    }

    // Función para dar de alta un coche, 
    // solo se podrá dar de baja un coche no disponible.
    function darDeAlta(coche) {
        if (coche.estado == 'No Disponible') {
            // Enviar solicitud para dar de baja
            fetch(`http://localhost:8080/coches/dar-de-alta/${coche.id}`, {
                method: 'PUT',
            })
                .then(response => {
                    if (response.ok) {
                        loadCoches();
                    }
                })
                .catch(error => {
                    console.error('Error al dar de baja:', error);
                });
        }       
    }


    // Función para cerrar los detalles de un coche.
    function closeDetails(detailsRow, detailsButton) {
        detailsRow.remove();
        detailsButton.textContent = '+';       
    }

    // Función para ordenar los coches por fecha.
    function sortCochesByDate() {
        cochesData.sort((a, b) => new Date(a.fechaIngreso) - new Date(b.fechaIngreso));
    }

    // Evento para detectar cambios en el filtro de fecha.
    dateFilter.addEventListener('change', () => {
        if (dateFilter.value == 'recientes') {
            if (!isSortedRecent) {
                sortCochesByDate();
                isSortedRecent = true;
            }
            renderCoches(cochesData);
        } else if (dateFilter.value == 'antiguos') {
            if (isSortedRecent) {
                sortCochesByDate();
                cochesData.reverse();
                isSortedRecent = false;
            }
            renderCoches(cochesData);
        }
    });

    // Utils Calendario.
    $(function () {
        $("#date-picker").datepicker({
            dateFormat: "yy-mm-dd" 
        });
    
        $("#search-button").click(function () {
            searchCoches();
        });
    });

    $("#search-button").click(function () {
        searchCoches();
    });

    // Funcion para buscar coches según la fecha señalada en el calendario.
    function searchCoches() {
        const selectedDate = $("#date-picker").val().trim();
        if (selectedDate == "" || selectedDate.length < 10) {
            // Si el campo de fecha está vacío o tiene una longitud incorrecta,
            // muestra todos los coches.
            renderCoches(cochesData);
        } else {
            const formattedDate = $.datepicker.formatDate('yy-mm-dd', new Date(selectedDate));
            // Filtrar los coches por la fecha seleccionada.
            const filteredCoches = cochesData.filter(coche => coche.fechaIngreso == formattedDate);
            renderCoches(filteredCoches);
        }
    }

    // Función para poder editar el precio de venta de los coches.
    function makePriceEditable(cell, coche) {
        cell.textContent = '';
    
        const precioInput = document.createElement('input');
        precioInput.className = 'editable-price';
        precioInput.type = 'text';
        precioInput.value = coche.precioVenta;
        cell.appendChild(precioInput);
    
    
        precioInput.addEventListener('keydown', (event) => {
            if (event.key === 'Enter') {
                const inputText = precioInput.value;
                // Comprobamos que sea válido.
                if (/^[0-9.]+$/.test(inputText)) {
                    const newPrice = parseFloat(inputText);                    
                    if (!isNaN(newPrice)) {
                        savePrice(coche, newPrice);
                    } else {
                        alert('El valor introducido no es un número válido.');
                    }
                } else {
                    alert('El valor introducido contiene caracteres no válidos.');
                }
            } else if (event.key === 'Escape') {
                loadCoches();
            }
        });
    
        precioInput.focus();
    }

    // Función para guardar el nuevo precio de venta de un coche
    function savePrice(coche, newPriceToSave) {
        // Enviar una solicitud para actualizar el precio
        fetch(`http://localhost:8080/coches/editar-precio/${coche.id}?newPrice=${newPriceToSave}`, {
            method: 'PUT',           
        })
        .then((response) => {
            if (response.ok) {
                loadCoches();          
            } else {        
                console.error('Error al actualizar el precio:', response.status);
            }
        })
        .catch((error) => {
            console.error('Error al actualizar el precio:', error);
        });
        
    }

});
