package at.gepardec.service;

public enum ServiceAction {
    OK1(0),
    ERROR1(1),
    ONEOUTTENERROR1(2),
    ONEOUTHUNDREDERROR1(3),
    DELAY1(4),
    OK2(5),
    ERROR2(6),
    ONEOUTTENERROR2(7),
    ONEOUTHUNDREDERROR2(8),
    DELAY2(9);

    int symbolNr;

    ServiceAction(int symbol) {
        this.symbolNr = symbol;
    }
}
