package com.example.Job.Offer.Analyst;

import java.util.Scanner;

public class UserCommunication {
    public String userURL() {
        System.out.println("Podaj poniżej stronę z której chcesz zobaczyć dane:");
        Scanner scanner= new Scanner(System.in);
        return scanner.nextLine();
    }
}
