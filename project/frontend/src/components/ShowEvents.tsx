import React, { useEffect, useState } from 'react';
import events from "../resources/test.json";
import '../styles/showEvents.css';
import Table from "react-bootstrap/Table";
import axios from 'axios';


export default function JsonDataDisplay() {

    const [appState, setAppState] = useState({
        loading: false,
        events: [] as any[]
    });

    useEffect(() => {
        setAppState({loading: true, events: []});
        const apiUrl = 'http://127.0.0.1:8080/ten_events_string';
        axios.get(apiUrl).then(res => {
            const allEvents = res.data
            setAppState({ loading: false, events: allEvents });
        })
    }, [setAppState])


    const displayData = appState.events.map(
        (events) => {
            return(
                <tr key = {events.id}>
                    <td>{events.id}</td>
                    <td>{events.name}</td>
                    <td>{events.city}</td>
                </tr>
            )
        }
    )
 
    return(
        <>
            <Table >
                <thead>
                    <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>City</th>
                    </tr>
                </thead>
                <tbody>
                    {displayData}
                </tbody>
            </Table>
        </>
    );
 }
