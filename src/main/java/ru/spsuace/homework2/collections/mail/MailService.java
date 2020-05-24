package ru.spsuace.homework2.collections.mail;


import ru.spsuace.homework2.collections.PopularMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Дополнительное задание:
 * Нужно создать сервис, который умеет обрабатывать некоторые базовые сообщения, а именно письма и зарплату.
 * Письма состоят из получателя, отправителя, текста сообщения
 * Зарплата состоит из получателя, отправителя и суммы.
 * Используйте ООП и основное задание для реализации данного дополнительного задания
 * <p>
 * Полный балл за этот класс: 6
 * Сделайте тест (alt + enter) на названии класса -> create test
 * и сделайте там 3 теста (3 простых разных случая для тестирования класса). Например тестКейс только с одним
 * получателем и отправителем, но большим количеством писем и зарплат, и проверьте что три основных метода возвращают то,
 * что должны (по аналогии с моими тестами). 1 тест - 1 балл
 * <p>
 * За все дополнительное задание в пакете mail можно получить 12 баллов
 */
public class MailService implements Consumer<BaseMail> {
    private PopularMap<String, List<BaseMail>> senderToMailBox;
    private PopularMap<String, List<BaseMail>> recipientToMailBox;

    public MailService() {
        this.senderToMailBox = new PopularMap<>();
        this.recipientToMailBox = new PopularMap<>();
    }

    /**
     * С помощью этого метода почтовый сервис обрабатывает письма и зарплаты
     * 2 балл
     */


    @Override
    public void accept(BaseMail o) {
        List<BaseMail> senderToMails = senderToMailBox.getOrDefault(o.getSender(), new ArrayList<>());
        senderToMails.add(o);
        senderToMailBox.put(o.getSender(), senderToMails);
        List<BaseMail> recipientToMails = recipientToMailBox.getOrDefault(o.getRecipient(), new ArrayList<>());
        recipientToMails.add(o);
        recipientToMailBox.put(o.getRecipient(), recipientToMails);
    }

    /**
     * Метод возвращает мапу получатель -> все объекты которые пришли к этому получателю через данный почтовый сервис
     * 1 балл
     */
    public Map<String, List<BaseMail>> getMailBox() {
        return recipientToMailBox;
    }


    /**
     * Возвращает самого популярного отправителя
     * 1 балл
     */
    public String getPopularSender() {
        return senderToMailBox.getPopularKey();
    }

    /**
     * Возвращает самого популярного получателя
     * 1 балл
     */
    public String getPopularRecipient() {
        return recipientToMailBox.getPopularKey();
    }

    /**
     * Метод должен заставить обработать service все mails.
     * 1 балл
     */
    public static void process(MailService service, List<BaseMail> baseMails) {
        for (BaseMail mail : baseMails) {
            service.accept(mail);
        }
    }
}

