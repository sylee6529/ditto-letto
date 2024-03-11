package center.unit.letter.domain.letter.type;

public enum MyLetterType {
    STRANGE("strange"),
    FRIEND("friend"),
    SENDING_STRANGER("sendingStranger"),
    SENDING_FRIEND("sendingFriend"),
    WAITING("waiting");

    private final String name;

    MyLetterType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MyLetterType determineLetterType(boolean isInContact, boolean arrived, boolean isSendingLetter) {
        // 편지가 도착했다면, 친구 유무에 따라 구분
        if(arrived) {
            return isInContact ? MyLetterType.FRIEND : MyLetterType.STRANGE;
        }

        // 도착하지 않은 오고 있는 편지는 모두 waiting
        if (!isSendingLetter) {
            return MyLetterType.WAITING;
        }

        // 도착하지 않은 보내는 편지는 친구 유무에 따라 구분
        return isInContact ? MyLetterType.SENDING_FRIEND : MyLetterType.SENDING_STRANGER;
    }
}

