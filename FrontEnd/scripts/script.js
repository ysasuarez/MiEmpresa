document.addEventListener("DOMContentLoaded", () => {
    const cochesList = document.querySelector(".coches-list");
    const dateFilter = document.getElementById("date-filter");
    let cochesData = [];
    let isSortedRecent = true; // Variable para rastrear el estado de orden.

    loadCoches();

    // Función para cargar los coches y mostrarlos.
    function loadCoches() {
        fetch("http://localhost:8080/coches")
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Error al obtener los coches");
                }
            })
            .then((data) => {
                cochesData = data; // Almacenar los datos de los coches.
                renderCoches(data);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    // Función para renderizar los coches en la tabla.
    function renderCoches(coches) {
        cochesList.innerHTML = ""; // Limpiar la tabla antes de mostrar los datos.

        coches.forEach((coche, index) => {
            const row = cochesList.insertRow();
            row.insertCell(0).textContent = coche.marca;
            row.insertCell(1).textContent = coche.matricula;
            row.insertCell(2).textContent = coche.precioVenta;
            const actionsCell = row.insertCell(3);
            const detailsButton = document.createElement('button');
            detailsButton.className = 'button-details';
            detailsButton.textContent = '+';
            detailsButton.addEventListener('click', () => {
                if (detailsButton.textContent == '+') {
                    showDetails(coche, detailsButton, row);
                } else if (detailsButton.textContent == '-') {
                    closeDetails(row.nextElementSibling, detailsButton);
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

        const detalles = [
            { title: 'ID', value: coche.id },
            { title: 'Coste', value: coche.coste },
            { title: 'Fecha de Ingreso', value: coche.fechaIngreso },
            { title: 'Estado', value: coche.vendido ? 'Vendido' : 'Disponible' },
            { title: 'Concesionario', value: 'Nombre del Concesionario' }
        ];

        detalles.forEach((detalle) => {
            const detailDiv = document.createElement('div');
            detailDiv.className = 'details-column';

            const titleDiv = document.createElement('div');
            titleDiv.className = 'detail-title';
            titleDiv.textContent = detalle.title;

            const valueDiv = document.createElement('div');
            valueDiv.className = 'detail-value';
            valueDiv.textContent = detalle.value;

            detailDiv.appendChild(titleDiv);
            detailDiv.appendChild(valueDiv);

            detailsContainer.appendChild(detailDiv);
        });

        detailsCell.appendChild(detailsContainer);
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
        if (dateFilter.value === 'recientes') {
            if (!isSortedRecent) {
                sortCochesByDate();
                isSortedRecent = true;
            }
            renderCoches(cochesData);
        } else if (dateFilter.value === 'antiguos') {
            if (isSortedRecent) {
                sortCochesByDate();
                cochesData.reverse();
                isSortedRecent = false;
            }
            renderCoches(cochesData);
        }
    });

   

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


    function searchCoches() {
        const selectedDate = $("#date-picker").val().trim();
        if (selectedDate === "" || selectedDate.length < 10) {
            // Si el campo de fecha está vacío o tiene una longitud incorrecta, muestra todos los coches
            renderCoches(cochesData);
        } else {
            const formattedDate = $.datepicker.formatDate('yy-mm-dd', new Date(selectedDate));
            // Filtrar los coches por la fecha seleccionada
            const filteredCoches = cochesData.filter(coche => coche.fechaIngreso === formattedDate);
            renderCoches(filteredCoches);
        }
    }
});
