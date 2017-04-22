class Box:
    def __init__(self, x1, y1, x2, y2):
        self.x1 = min(x1, x2)
        self.x2 = max(x1, x2)
        self.y1 = min(y1, y2)
        self.y2 = max(y1, y2)

    def __str__(self):
        return "{},{} {},{}".format(self.y1, self.x1, self.y2, self.x2)

    def checkCollision(self, other_box):
        return not (other_box.x2 < self.x1 or other_box.x1 > self.x2 or other_box.y2 < self.y1 or other_box.y1 > self.y2)

cases = int(input())
for sfjhoe in range(cases):
    data_boxes = []
    query_boxes = []
    numdata = int(input())
    for j in range(numdata):
        # TODO save data boxes
        pairs = input().split(" ")
        pair1 = pairs[0].split(",") # y1,x1 y1 = pair1[0]
        pair2 = pairs[1].split(",") # y2,x2
        box = Box(int(pair1[1]), int(pair1[0]), int(pair2[1]), int(pair2[0]))
        if (box.x1 > box.x2):
            data_boxes.append(Box(box.x1, box.y1, 180, box.y2))
            data_boxes.append(Box(-180, box.y1, box.x2, box.y2))
        else:
            data_boxes.append(box)
    numquery = int(input())
    for j in range(numquery):
        # TODO save query boxes
        pairs = input().split(" ")
        pair1 = pairs[0].split(",")
        pair2 = pairs[1].split(",")
        box = Box(int(pair1[1]), int(pair1[0]), int(pair2[1]), int(pair2[0]))
        if (box.x1 > box.x2):
            query_boxes.append(Box(box.x1, box.y1, 180, box.y2))
            query_boxes.append(Box(-180, box.y1, box.x2, box.y2))
        else:
            query_boxes.append(box)
    # TODO do other stuff
    anyFound = False
    for i, qbox in enumerate(query_boxes):
        for j, dbox in enumerate(data_boxes):
            #print("testing query box: ", qbox, " with data box: ", dbox)
            if qbox.checkCollision(dbox):
                del data_boxes[j]
                print(dbox)
                anyFound = True
    if not anyFound:
        print("No data found.")
    else:
        print()
