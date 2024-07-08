![311573744-62be9bd6-91f4-4c31-af0e-906bc3843eeb](https://github.com/sylee6529/ditto-letto/assets/68765200/747df790-b13a-4c77-937c-87ac0bc5a056)

## 핵심 기능
### 💌 (아날로그 편지 기능) 마음을 담아 편지를 보냅니다

> 옛날, 전화번호부를 뒤적거려 문자를 보냈던 그 시절로 돌아갑니다. <br/>
나를 나타낼 수 있는 건 오직 전화번호뿐! <br/>
친구, 연인, 썸, 가족과 편지를 주고받아요.
> 

### 🔒 (문자 암호화) 우리만의 2000년대 암호 편지,
Ꮣㅓ己б ⓩi금 정말 ┓┠ㄲŀ࿁ㅣ 있ㄴΓ봐..

> 암호 편지로 전송하면, 2000년대 유행 언어로 바꾸어 보내드립니다.
내가 굳이 바꾸지 않아도, 자연스럽게 2000년대 유행 언어로 전송! 
친구와 그때 그 시절의 사람처럼 메시지를 전송하는 기분을 느껴보세요!
> 

### ↔️ (거리, 랜덤 가상 운송수단 기반 문자 배송) 언제 어디서나 빠른 요즘,
스마트 시대에도 서로의 거리를 느껴보아요!

> 상대방과의 거리에 따라 운송 수단과 전송 속도가 결정됩니다.
말을 타고 나에게로 달려오는 편지를 두근두근 떨리는 마음으로 기다려보세요.
>

## 맡은 부분 리뷰

### 💌 랜덤 가상 운송수단으로 편지 배송하기 💌
<img src="https://github.com/sylee6529/ditto-letto/assets/68765200/c4b52e79-e566-4f6b-beb3-9a2de35a1722" alt="이미지 설명" width="500">

가상 운송수단이 걸리는 시간을 계산하기 전에, 위의 표와 같이 `최소/최대 거리`, `속도`, `운송수단 타입`(일반/이벤트)를 우선 정의하고, 구현하였다. <br/>

- 최소 시간은 1초, 최대 시간 24시간으로 설정
- 최대 약 0.111m의 오차, 즉 100m 내는 같은 위치로 간주 (경도위도를 6자리 까지)
  
- 이벤트 날짜의 경우, 오늘이 이벤트 날짜를 지난 경우, 내년 날짜를 도착일로 <br/>
  오늘이 이벤트 날짜 전인 경우, 이번 년도 날짜를 도착일로 구현하는 등, 다양한 케이스를 고려하여 운송 시간 계산 로직 구현


**테스트 결과**
- 서울-부산은 달리기 최대 거리(42.195km)를 넘어, 달리기 제외 모든 운송 수단이 편지 배달이 된다.
- 광화문-건대입구는 자동차, 비행기, 기차의 최소거리보다 적은 거리이기 때문에, 이 3가지 외 모든 운송 수단으로 편지 배달이 된다.
<img src="https://github.com/sylee6529/ditto-letto/assets/68765200/2ce62d9b-67c0-434e-9245-bcf5cb8ccfc7" alt="이미지 설명" width="500">

**구현 코드**

1. 편지를 보내기 전에, 보내는 사람과 받는 사람의 위치 설정 여부에 따라 편지 발송 여부를 결정
- `if (편지 받는 사람의 위치 설정여부 == false) { 편지를 보낼 수 없다. }` // 거리 계산 불가하므로
- `if (편지 받는 사람의 위치 설정 여부 == true && (편지 보내는 사람의 발송 시점 당시 위치 정보 == true || 편지 보내는 사람의 위치 설정 여부 == true) { 이 경우는 편지를 보낼 수 있다. }`
  - `편지 받는 사람의 위치 설정 여부 == false`인 경우는, 위의 if문에서 걸러지므로 `편지 보내는 사람의 발송 시점 당시 위치 정보 == flase && 편지 보내는 사람의 위치 설정 여부 == false`인 경우 편지를 보낼 수 없다.
    
  ```java
  User toUser = userFacade.getUser(request.getToPhoneNumber());
  validate(request, toUser, fromUser);
  updateLocation(fromUser, request);
  ```
  ```java
  private void validate(SendLetterRequest request, User toUser, User fromUser) {
      // request에 위치 정보가 있으면, toUser의 위치 정보가 없을 때만 에러
      if(request.getLatitude() != null && request.getLongitude() != null) {
          Optional.ofNullable(toUser.getLocation())
                  .orElseThrow(ToLocationNotFoundException::new);
      }
  
      // request에 위치 정보가 없고 fromUser와 toUser의 위치 정보가 없으면, 에러
      else {
          Optional.ofNullable(fromUser.getLocation())
                  .orElseThrow(FromLocationNotFoundException::new);
          Optional.ofNullable(toUser.getLocation())
                  .orElseThrow(ToLocationNotFoundException::new);
      }
  }
  
  public void updateLocation(User fromUser, SendLetterRequest request) {
      if(request.getLatitude() != null && request.getLongitude() != null) {
          updateUserService.updateLocation(fromUser, new UpdateUserLocationRequest(request.getLongitude(), request.getLatitude()));
      }
  }
  ```

2. 보내는 사람의 위치와 받는 사람의 위치 거리를 계산 + 랜덤 가상수단 결정 -> 시간 계산
- 보내는 사람과 받는 사람의 위도, 경도 값으로 Haversine 공식을 사용하여 거리를 구함
- 거리를 바탕으로 최대거리/최소거리 조건에 맞지 않는 가상 수단은 제외하고, 이벤트 기간에 속해있다면 이벤트 타입도 포함하여 가상 수단 리스트를 만들고, 이 중 랜덤으로 뽑는다.

  ```java
  public Medium getMediumByDistance(double distance) {
      // 거리가 최대 거리 이상이면, 최대 거리로 설정
      distance = Math.min(distance, Medium.DEFAULT_MAX_DISTANCE);
  
      // 이벤트 타입까지 포함하여 타입을 결정
      List<Medium> mediumList = mediumRepository.findAllByDistance(distance)
              .orElseThrow(() -> new MediumException(MediumErrorCode.MEDIUM_NOT_FOUND));
      mediumList.addAll(mediumRepository.findAllByIsEvent(true)
              .orElseThrow(() -> new MediumException(MediumErrorCode.EVENT_MEDIUM_NOT_FOUND)));
  
      return mediumList.get(new Random().nextInt(mediumList.size()));
  }
  ```

3. 편지 저장 완료.

<br/>

### 2. 📫 나의 편지함 조회 📫

- 기본적으로 편지 조회는, `보낸사람=나 or 받는 사람=나`인 모든 편지 리스트를 가져온 후 아래의 로직을 거친다.
  - 편지 타입에 따라, UI 컴포넌트와 디자인이 다르기 때문에 나누어 응답을 반환해야 한다. <br/>
    - `STRANGER`(친구가 아닌 사람에게서 받은 도착완료 편지)
    - `FRIEND`(친구에게서 받은 도착 완료 편지)
    - `SENDING_STRANGER`(친구가 아닌 사람에게 보내는 미도착 편지)
    - `SENDING_FRIEND`(친구에게 보내는 미도착 편지)
       `WAITING` (나에게 오고 있는 모든 편지)
  ```java
  MyLetterType myLetterType = MyLetterType.determineLetterType(false, letter.isArrived(), isSendingLetter);
      String previewText = myLetterType == MyLetterType.WAITING? letter.getPreviewText() : null;
    
      myLetters.add(
              new MyLetterVO(
                      letter.getId(),
                      myLetterType.getName(),
                      letter.getMediumType(),
                      letter.getCreatedAt(),
                      letter.getArriveAt(),
                      previewText
              )
      );
  ```
  ```java
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
  ```

⚠️ 문제점: 탈퇴 유저의 경우 Hard Delete로 유저를 삭제하기로 했는데, 편지 조회 부분에서 User Entity 접근이 필수적이기 때문에 Runtime Exception 다수 발생

✔️ 해결: 편지 조회 관련 유저 접근 전, 존재하는 유저인지 체크한다.
  - 다만, 탈퇴 유저는 DB 상에 존재하지 않지만 (마치 전화번호를 바꾼 사람처럼)  <br/>
  '전화번호부'(contact)에는 존재해야 하는 기획 상, 친구인 경우 Contact 정보로 탈퇴 유저의 편지에 접근한다.

  
      ```java
      User otherUser = isSendingLetter? letter.getTo() : letter.getFrom();
      if(!userRepository.existsById(otherUser.getId())) {
          // 탈퇴하지 않은 유저의 편지 조회 로직
      } else {
          // 탈퇴한 유저인 경우의 편지 조회 로직
      }
      ```



**위의 모든 구현 사항은 처음에 Service 클래스에 모든 로직을 작성하였지만, 가독성이 너무 좋지 않고 유지보수가 어려워 최대한 도메인과 관련된 클래스에 메소드를 분리하였습니다!**

## 느낀점

- 비즈니스 요구사항을 빼놓지 않고 조건을 잘 맞추어 구현하기란 정말 쉽지 않음을 느꼈다.
- 클라이언트가 달라져도, 백엔드 API 설계는 동일하게 하는 것이라고 생각했던 착각을 벗엇다.
  - 편지를 보내는 API에 응답 값에 운송수단 이미지가 필요한 화면이 있어서, 당연하게 클라이언트에 정적 파일로 두고 가져와 사용하는 것을 생각하고 운송 수단 이름(ex. HORSE)만을 포함하여 Response 값을 설계하였다. <br/>
    🗣️ Client분: `운송 수단 같이 이벤트 타입을 포함하여 변동/추가 가능성이 높은 것은 이미지 URL도 보내주셔야 해요.` <br/>
    라고 말씀해주셔서, 앱 서비스는 업데이트를 웹만큼 자주 하기 어렵다는 것을 인지하였고, 이를 고려하여 API 설계를 해야겠다고 생각하였다.

## 기술 스택
- Spring Boot, Java 17, Spring Data JPA, PostgreSQL
- Client: iOS, Swift, SwiftUI

## DB 구조

![image](https://github.com/sylee6529/ditto-letto/assets/68765200/44f2e82a-455e-4c9c-abb6-bf020bf82959)




