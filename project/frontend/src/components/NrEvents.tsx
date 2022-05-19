import axios from "axios";
import React, { useEffect, useState } from "react";
import '../styles/greetingText.css';

/**
 * Gets the number of events to display in the greeting text
 * @returns number of events
 */

export default function NrEvents(){
    const [nrEvents, setNrEvents] = useState({
        nr: "" as String
    });

    useEffect(() => {
        setNrEvents({nr: ""})
        const apiUrl = 'http://localhost:8080/number_events';
        axios.get(apiUrl).then(res => {
            const result = res.data
            setNrEvents({nr: result})
        })
    }, [setNrEvents])

    const number =
    <h3 className="nr-events">
        {nrEvents.nr}
    </h3>
    
    return (
        number
    );
}
