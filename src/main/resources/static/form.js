const typeSelect = document.querySelector('select[name=type]');
const originSelect = document.querySelector('select[name=origin]');
const submitButton = document.querySelector('button[type=submit]');

function init() {
    typeSelect.addEventListener('change', ev => {
        const type = ev.target.value;
        if (type) updateOrigins(type);
    });
}

function updateOrigins(type) {
    fetch(`/types`)
        .then(res => res.json())
        .then(json => {
            const url = json.filter(t => t.type === type)
                .map(t => t['links'].filter(l => l.rel === 'origins').map(l => l.href));
            console.log(url, )
            fetch(url)
                .then(res => res.json())
                .then(json => {
                    originSelect.querySelectorAll('option').forEach(el => {
                        if (el.value) el.remove();
                    });
                    originSelect.removeAttribute('disabled');
                    submitButton.removeAttribute('disabled');
                    json.forEach(origin => {
                            const option = document.createElement('option');
                            option.value = option.innerText = origin;
                            originSelect.appendChild(option);
                        })
                });
        });
}

init();