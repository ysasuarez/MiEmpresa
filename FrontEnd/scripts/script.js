document.addEventListener("DOMContentLoaded", () => {
    const cochesTable = document.querySelector(".coches-table");
    const cochesList = document.querySelector(".coches-list");

    function showDetails(coche, detailsButton, row) {
        const detailsRow = cochesList.insertRow(row.rowIndex);
        const detailsCell = detailsRow.insertCell(0);
        detailsCell.colSpan = 4;

        const detailsContainer = document.createElement('div');
        detailsContainer.className = 'coche-details';

        detailsContainer.innerHTML = `ID: ${coche.id}<br>
                                Coste: ${coche.coste}<br>
                                Fecha de Ingreso: ${coche.fechaIngreso}<br>
                                Estado: ${coche.vendido ? 'Vendido' : 'Disponible'}`;

        const closeButton = document.createElement('button');
        closeButton.className = 'close-button';
        closeButton.textContent = 'x';
        closeButton.addEventListener('click', () => {
            detailsRow.remove();
            detailsButton.disabled = false;
        });

        detailsContainer.appendChild(closeButton);
        detailsCell.appendChild(detailsContainer);

        detailsButton.disabled = true;
    }

    fetch("http://localhost:8080/coches")
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Error al obtener los coches");
            }
        })
        .then((data) => {
            data.forEach((coche, index) => {
                const row = cochesList.insertRow();
                row.insertCell(0).textContent = coche.marca;
                row.insertCell(1).textContent = coche.matricula;
                row.insertCell(2).textContent = coche.precioVenta;
                const actionsCell = row.insertCell(3);
                const detailsButton = document.createElement('button');
                detailsButton.className = 'button-details';
                detailsButton.textContent = '+';
                detailsButton.addEventListener('click', () => showDetails(coche, detailsButton, row));
                actionsCell.appendChild(detailsButton);
            });
        })
        .catch((error) => {
            console.error(error);
        });
});
