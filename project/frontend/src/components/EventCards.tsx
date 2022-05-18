import '../styles/showEvents.css';
import '../styles/showEventCat.css';
import {event} from "./Models";
import { EventCard } from "./EventCard";
import { useState } from 'react';

/**
 * Creates a list of event cards.
 * @param events is the list of the events
 * @returns a list of event cards
 */
export function EventCards(events: event[]) : JSX.Element[] {
    const res = events.map((e: event) => {
        return EventCard(e)
    })
    return res
}