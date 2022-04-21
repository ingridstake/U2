import React, { useEffect, useState } from 'react';
import events from "../resources/test.json";
import '../styles/showEvents.css';
import Table from "react-bootstrap/Table";
import axios from 'axios';


function JsonDataDisplay() {

    const [appState, setAppState] = useState({
        
        loading: false,
        events: [] as any[],
      });
    
    /*
    useEffect(() => {
        //setAppState({ loading: true });
        const apiUrl = 'http://127.0.0.1:8080/ten_events_string';
        axios.get(apiUrl).then((events) => {
          const allEvents = events.data;
          //setAppState({ loading: false, events: allEvents });
        });
      });           // [setAppState]
      */
    
    useEffect(() => { 
        setAppState({ loading: true, events: [] });
        let allEvents = [
            {
            id: '',
            name: '',
            city: '',
            },]
        
        const apiUrl = 'http://127.0.0.1:8080/ten_events_string';
        axios.get(apiUrl).then(res => {
            const newItem = {
              id: res.data.id,
              name: res.data.name,
              city: res.data.city,
            };
            allEvents.push(newItem);
         })
        
        /*.then((events) => {
          const allEvents = events.data;
          console.log(allEvents);
          */
          setAppState({ loading: false, events: allEvents });
        
        //});
       
    }, [setAppState]);

    const DisplayData=appState.events.map(
        (events)=>{
            return(
                <tr key={events.id}>
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
                    {DisplayData}
                </tbody>
            </Table>
        </>
    );
 }

export default JsonDataDisplay;
