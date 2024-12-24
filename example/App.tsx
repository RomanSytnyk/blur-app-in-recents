import BlurAppInRecents from "@roman.sytnyk/blur-app-in-recents";
import React from "react";
import { Button, SafeAreaView, ScrollView, Text, View } from "react-native";

export default function App() {

  React.useEffect(() => {
    BlurAppInRecents.enable();
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView style={styles.container}>
        <Group name="Blur - enable">
          <Button
            title="Set to true"
            onPress={() => {
              BlurAppInRecents.enable();
            }}
          />
        </Group>
        <Group name="Blur disable">
          <Button
            title="Set to false"
            onPress={() => {
              BlurAppInRecents.disable();
            }}
          />
        </Group>
      </ScrollView>
    </SafeAreaView>
  );
}

function Group(props: { name: string; children: React.ReactNode }) {
  return (
    <View style={styles.group}>
      <Text style={styles.groupHeader}>{props.name}</Text>
      {props.children}
    </View>
  );
}

const styles = {
  header: {
    fontSize: 30,
    margin: 20,
  },
  groupHeader: {
    fontSize: 20,
    marginBottom: 20,
  },
  group: {
    margin: 20,
    backgroundColor: "#fff",
    borderRadius: 10,
    padding: 20,
  },
  container: {
    flex: 1,
    backgroundColor: "#eee",
  },
  view: {
    flex: 1,
    height: 200,
  },
};
