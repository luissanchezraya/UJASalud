document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'es',
        dateClick: function(info) {
            window.location.href = `citas.xhtml?fecha=${info.dateStr}`;
        }
    });
    calendar.render();
});