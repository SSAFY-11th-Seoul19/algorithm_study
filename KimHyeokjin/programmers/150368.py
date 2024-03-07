from itertools import product

def solution(users, emoticons):
    #users -> n명의 구매기준을 담은 2차원 정수배열
    #[[비율, 최대가격], [비율, 최대가격]]
    #emoticons -> 이모티콘의 정가 가격
    #[가격1, 가격2]
    #answer -> 임티플 가입수, 이모티콘 매출액
    answer = []
    maxCnt = 0
    maxVal = 0
    # 할인율 10%, 할인율20%, 할인율30%, 할인율 40%
    discountRate = [10, 20, 30, 40]
    eLen = len(emoticons)
    for lst in product(discountRate, repeat = eLen):
        disEmoticons = []

        for i in range(eLen):
            disEmoticons.append(emoticons[i]-emoticons[i]//100*lst[i])

        totalPrice = 0
        pCount = 0
        for i in range(len(users)):
            tmpPrice = 0
            for j in range(eLen):
                if users[i][0] <= lst[j]:
                    tmpPrice += disEmoticons[j]

            if tmpPrice >= users[i][1]:
                pCount +=1
                tmpPrice = 0
                continue
            else:
                totalPrice+=tmpPrice

        if maxCnt <pCount:
            maxCnt = pCount
            maxVal = totalPrice
            continue

        if maxCnt==pCount:
            maxVal = max(maxVal, totalPrice)
            continue
    answer=[maxCnt, maxVal]

    ##IDEA. 4Pn해서 모든 가짓수 구한 다음에 계산
        
    return answer
