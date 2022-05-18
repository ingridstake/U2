import axios from "axios";
import React, { useEffect, useState } from "react";


export default function NrEvents(){
    const [nrEvents, setNrEvents] = useState({
        nr: "" as String
    });

    useEffect(() => {
        setNrEvents({nr: ""})
        const apiUrl = 'http://127.0.0.1:8080/number_events';
        axios.get(apiUrl).then(res => {
            const result = res.data
            setNrEvents({nr: result})
            debugger;
        })
    }, [setNrEvents])

    const number = nrEvents.nr
    
    return (
        number
    );
}
