import React from "react";

function EventList() {
    const events = ['Beertasting', 'Mares', 'Gasquen'];
    const listItems = events.map((event) =>
    <li>{event}</li>
    );
    return (
        <ul>{listItems}</ul>
    )
}

export default EventList;
